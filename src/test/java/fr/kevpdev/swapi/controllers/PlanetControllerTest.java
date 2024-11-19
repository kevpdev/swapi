package fr.kevpdev.swapi.controllers;

import fr.kevpdev.swapi.dtos.PlanetDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.PlanetService;
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

@WebFluxTest(PlanetController.class)
class PlanetControllerTest {

    private static final String PATH = "/api/v1/planets";
    private PlanetDTO planetDTO;

    private SearchResultDTO<PlanetDTO> searchResultDTO;

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PlanetService planetService;

    @BeforeEach
    void setUp() {

         this.planetDTO = PlanetDTO.builder()
                .name("Yavin IV")
                .rotationPeriod("24")
                .orbitalPeriod("4818")
                .diameter("10200")
                .climate("temperate, tropical")
                .gravity("1 standard")
                .terrain("jungle, rainforests")
                .surfaceWater("8")
                .population("1000")
                .residents(List.of()) // Liste vide
                .films(List.of("https://swapi.dev/api/films/1/"))
                .created("2014-12-10T11:37:19.144000Z")
                .edited("2014-12-20T20:58:18.421000Z")
                .url("https://swapi.dev/api/planets/3/")
                .build();

    }

    @Test
    public void shouldReturnFilmByIdWhenFilmIdValid() {

        when(planetService.getPlanetById(1)).thenReturn(Mono.just(planetDTO));

        webTestClient.get().uri(PATH+"/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(PlanetDTO.class)
                .isEqualTo(planetDTO);

        verify(planetService).getPlanetById(1);
    }

    @Test
    public void shouldReturn404ErrorFilmByIdWhenFilmNotFound() {

        when(planetService.getPlanetById(1)).thenReturn(
                Mono.error(
                        WebClientResponseException
                                .create(404, "Film not Found", null, null, null)));

        webTestClient.get().uri(PATH+"/1")
                .exchange()
                .expectStatus().isNotFound();

        verify(planetService).getPlanetById(1);

    }

    /*
        TODO...
     */
}