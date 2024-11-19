package fr.kevpdev.swapi.services;

import fr.kevpdev.swapi.dtos.FilmDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import reactor.core.publisher.Mono;

public interface FilmService {

    Mono<FilmDTO> getFilmById(Integer id);
    Mono<SearchResultDTO<FilmDTO>> getFilms(Integer pageId);
    Mono<SearchResultDTO<FilmDTO>> getFilmByTitle(String title);
}
