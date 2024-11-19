package fr.kevpdev.swapi.controllers;

import fr.kevpdev.swapi.dtos.FilmDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<FilmDTO>> getFilmById(@PathVariable int id) {
        return filmService.getFilmById(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping
    public Mono<ResponseEntity<SearchResultDTO<FilmDTO>>> getFilms(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId) {
        return filmService.getFilms(pageId)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<SearchResultDTO<FilmDTO>>> getFilmByName(@RequestParam String title) {
        return filmService.getFilmByTitle(title)
                .map(ResponseEntity::ok);
    }
}
