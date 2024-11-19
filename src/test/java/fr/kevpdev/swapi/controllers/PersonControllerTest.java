package fr.kevpdev.swapi.controllers;

import fr.kevpdev.swapi.dtos.PersonDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest(PersonController.class)
class PersonControllerTest {

    private static final String PATH = "/api/v1/people";
    private PersonDTO personDTO;

    private SearchResultDTO<PersonDTO> searchResultDTO;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PersonService personService;

    @BeforeEach
    public void setup() {
        this.personDTO = PersonDTO.builder()
                .name("Luke Skywalker")
                .birthYear("19BBY")
                .eyeColor("Blue")
                .gender("Male")
                .hairColor("Blond")
                .height("172")
                .mass("77")
                .homeworld("https://swapi.dev/api/planets/1/")
                .films(List.of("https://swapi.dev/api/films/1/", "https://swapi.dev/api/films/2/"))
                .species(List.of())
                .starships(List.of("https://swapi.dev/api/starships/12/"))
                .vehicles(List.of("https://swapi.dev/api/vehicles/14/"))
                .created("2014-12-09T13:50:51.644000Z")
                .edited("2014-12-20T21:17:56.891000Z")
                .url("https://swapi.dev/api/people/1/")
                .build();

        this.searchResultDTO = SearchResultDTO.<PersonDTO>builder()
                .count(82)
                .previous(null)
                .results(List.of(personDTO))
                .build();
    }


    @Test
    public void ShouldReturnPersonByIdWhenStatusIsOK() {

        when(personService.getPersonById(1)).thenReturn(Mono.just(personDTO));

        webTestClient.get().uri(PATH+"/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PersonDTO.class)
                .isEqualTo(personDTO);

        verify(personService).getPersonById(1);

    }

    @Test
    public void ShouldTReturn404ErrorWhenEmptyPerson() {

        when(personService.getPersonById(100))
                .thenReturn(Mono
                        .error(WebClientResponseException
                                .create(404,"", null, null, null)));

        webTestClient.get().uri(PATH+"/100")
                .exchange()
                .expectStatus().isNotFound();

        verify(personService).getPersonById(100);

    }

        @Test
        public void ShouldReturnAllPersonWhenPageIdNotEmpty() {

            shouldReturnAllPeopleWhenStatusOK(1);

        }

        @Test
        public void ShouldReturnAllPersonWhenEmptyPageId() {

            shouldReturnAllPeopleWhenStatusOK(null);

        }

        /**
         * Test to retrieve all people when status is OK with a pageId param.
         * If pageId is null, then default page number is 1
         * @param pageId Page number for the pagination
         */
        private void shouldReturnAllPeopleWhenStatusOK(Integer pageId) {

            when(personService.getPeople(1)).thenReturn(Mono.just(searchResultDTO));

            webTestClient.get()
                    .uri(uriBuilder -> {
                        var builder = uriBuilder.path(PATH);
                        if (pageId != null) {
                            builder.queryParam("page", pageId);
                        }
                        return builder.build();
                    })
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody(new ParameterizedTypeReference<SearchResultDTO<PersonDTO>>() {})
                    .isEqualTo(searchResultDTO);

            verify(personService).getPeople(1);
        }


    @Test
    public void ShouldReturn404ErrorWhenSearchResultisNotFound() {

        when(personService.getPeople(1)).thenReturn(Mono
                .error(WebClientResponseException
                        .create(404,"", null, null, null)));

        webTestClient.get().uri(uriBuilder -> uriBuilder
                        .path(PATH)
                        .queryParam("page", 1)
                        .build())
                .exchange()
                .expectStatus().isNotFound();

        verify(personService).getPeople(1);

    }

    @Test
    public void ShouldReturnPersonByNameWhenStatusIsOk() {

        when(personService.getPersonByName(anyString())).thenReturn(Mono.just(searchResultDTO));
        String name = "Luke SkyWalker";
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path(PATH+"/search")
                        .queryParam("name", name)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<SearchResultDTO<PersonDTO>>() {})
                .isEqualTo(searchResultDTO);

        verify(personService).getPersonByName(name);

    }

    @Test
    public void ShouldReturn404ErrorWhenPersonByNameIsNotFound() {

        String name = "Luke SkyWalker";

        when(personService.getPersonByName(name))
                .thenReturn(Mono
                        .error(WebClientResponseException
                                .create(404,"", null, null, null)));

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path(PATH+"/search")
                        .queryParam("name", name)
                        .build())
                .exchange()
                .expectStatus().isNotFound();

        verify(personService).getPersonByName(name);

    }
}