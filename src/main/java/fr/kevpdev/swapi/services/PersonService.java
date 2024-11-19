package fr.kevpdev.swapi.services;

import fr.kevpdev.swapi.dtos.PersonDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import reactor.core.publisher.Mono;


public interface PersonService {

    Mono<PersonDTO> getPersonById(Integer id);
    Mono<SearchResultDTO<PersonDTO>> getPeopleAll(Integer pageId);
    Mono<SearchResultDTO<PersonDTO>> getPersonByName(String name);

}
