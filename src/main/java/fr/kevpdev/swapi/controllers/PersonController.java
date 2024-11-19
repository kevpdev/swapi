package fr.kevpdev.swapi.controllers;

import fr.kevpdev.swapi.dtos.PersonDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PersonDTO>> getPeopleById(@PathVariable int id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Mono<ResponseEntity<SearchResultDTO<PersonDTO>>> getPeopleAll(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId) {
        return personService.getPeopleAll(pageId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public Mono<ResponseEntity<SearchResultDTO<PersonDTO>>> getPeopleByName(@RequestParam String name) {
        return personService.getPersonByName(name)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
