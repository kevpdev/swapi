package fr.kevpdev.swapi.services;

import fr.kevpdev.swapi.dtos.PlanetDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import reactor.core.publisher.Mono;

public interface PlanetService {

    Mono<PlanetDTO> getPlanetById(Integer id);
    Mono<SearchResultDTO<PlanetDTO>> getPlanets(Integer pageId);
    Mono<SearchResultDTO<PlanetDTO>> getPlanetByName(String name);
}
