package fr.kevpdev.swapi.controllers;

import fr.kevpdev.swapi.dtos.PlanetDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.PlanetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/planets")
public class PlanetController {
    
    private final PlanetService planetService;

    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PlanetDTO>> getPlanetById(@PathVariable int id) {
        return planetService.getPlanetById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Mono<ResponseEntity<SearchResultDTO<PlanetDTO>>> getPlanets(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId) {
        return planetService.getPlanets(pageId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<SearchResultDTO<PlanetDTO>>> getPlanetByName(@RequestParam String name) {
        return planetService.getPlanetByName(name)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
