package fr.kevpdev.swapi.controllers;

import fr.kevpdev.swapi.dtos.FilmDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest(FilmController.class)
class FilmControllerTest {

    private static final String PATH = "/api/v1/films";
    private FilmDTO filmDTO;

    private SearchResultDTO<FilmDTO> searchResultDTO;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FilmService filmService;

    @BeforeEach
    public void setup() {
        this.filmDTO = FilmDTO.builder()
                .title("A New Hope")
                .episodeId(4)
                .openingCrawl("It is a period of civil war...")
                .director("George Lucas")
                .producer("Gary Kurtz, Rick McCallum")
                .releaseDate("1977-05-25")
                .characters(List.of(
                        "https://swapi.dev/api/people/1/",
                        "https://swapi.dev/api/people/2/",
                        "https://swapi.dev/api/people/3/",
                        "https://swapi.dev/api/people/4/"
                ))
                .planets(List.of(
                        "https://swapi.dev/api/planets/1/",
                        "https://swapi.dev/api/planets/2/",
                        "https://swapi.dev/api/planets/3/"
                ))
                .starships(List.of(
                        "https://swapi.dev/api/starships/2/",
                        "https://swapi.dev/api/starships/3/",
                        "https://swapi.dev/api/starships/5/"
                ))
                .vehicles(List.of(
                        "https://swapi.dev/api/vehicles/4/",
                        "https://swapi.dev/api/vehicles/6/"
                ))
                .species(List.of(
                        "https://swapi.dev/api/species/1/",
                        "https://swapi.dev/api/species/2/"
                ))
                .created("2014-12-10T14:23:31.880000Z")
                .edited("2014-12-20T19:49:45.256000Z")
                .url("https://swapi.dev/api/films/1/")
                .build();


        this.searchResultDTO = SearchResultDTO.<FilmDTO>builder()
                .count(6)
                .previous(null)
                .results(List.of(filmDTO))
                .build();
    }

    @Test
    public void shouldReturnFilmByIdWhenFilmIdValid() {

        when(filmService.getFilmById(1)).thenReturn(Mono.just(filmDTO));

        webTestClient.get().uri(PATH+"/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(FilmDTO.class)
                .isEqualTo(filmDTO);

        verify(filmService).getFilmById(1);
    }

    @Test
    public void shouldReturn404ErrorFilmByIdWhenFilmNotFound() {

        when(filmService.getFilmById(1)).thenReturn(
                Mono.error(
                        WebClientResponseException
                                .create(404, "Film not Found", null, null, null)));

        webTestClient.get().uri(PATH+"/1")
                .exchange()
                .expectStatus().isNotFound();

        verify(filmService).getFilmById(1);

    }

    /*
         TODO...
     */

}