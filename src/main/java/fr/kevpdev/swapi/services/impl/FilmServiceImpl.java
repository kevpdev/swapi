package fr.kevpdev.swapi.services.impl;

import fr.kevpdev.swapi.dtos.FilmDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.FilmService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FilmServiceImpl implements FilmService {

    private final WebClient webClient;

    public FilmServiceImpl(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("https://swapi.dev/api/films").build();
    }

    /**
     * Retrives a film by ID
     * @param id film ID
     * @return a Mono containing the FilmDTO object if found or empty if not
     */
    @Override
    public Mono<FilmDTO> getFilmById(Integer id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(FilmDTO.class);
    }

    /**
     * Retrives all film with pagination
     *
     * @param pageId Pagination number
     * @return a Mono containing the filmDTO object containing a FilmDTO array
     */
    @Override
    public Mono<SearchResultDTO<FilmDTO>> getFilms(Integer pageId) {
        return webClient.get()
                .uri("/?page={pageId}", pageId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SearchResultDTO<FilmDTO>>() {});
    }

    /**
     * Retrives all films by title
     * @param title film title
     * @return a Mono containing the FilmDTO object list if found or empty if not
     */
    @Override
    public Mono<SearchResultDTO<FilmDTO>> getFilmByTitle(String title) {
        return webClient.get()
                .uri("/?search={title}", title)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SearchResultDTO<FilmDTO>>() {});
    }
}
