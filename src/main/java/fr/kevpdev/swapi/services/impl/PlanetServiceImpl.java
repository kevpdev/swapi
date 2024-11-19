package fr.kevpdev.swapi.services.impl;

import fr.kevpdev.swapi.dtos.PlanetDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.PlanetService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PlanetServiceImpl implements PlanetService {

    private final WebClient webClient;

    public PlanetServiceImpl(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("https://swapi.dev/api/planets").build();
    }

    /**
     * Retrives a planet by ID
     * @param id planet ID
     * @return a Mono containing the PlanetDTO object if found or empty if not
     */
    @Override
    public Mono<PlanetDTO> getPlanetById(Integer id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(PlanetDTO.class);
    }

    /**
     * Retrives all planets with pagination
     *
     * @param pageId Pagination number
     * @return a Mono containing the SearchResultDTO object containing a PlanetDTO array
     */
    @Override
    public Mono<SearchResultDTO<PlanetDTO>> getPlanets(Integer pageId) {
        return webClient.get()
                .uri("/?page={pageId}", pageId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SearchResultDTO<PlanetDTO>>() {});
    }

    /**
     * Retrives all planets by name
     * @param name planet name
     * @return a Mono containing the PlanetDTO object list if found or empty if not
     */
    @Override
    public Mono<SearchResultDTO<PlanetDTO>> getPlanetByName(String name) {
        return webClient.get()
                .uri("/?search={name}", name)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SearchResultDTO<PlanetDTO>>() {});
    }
}
