package fr.kevpdev.swapi.services.impl;

import fr.kevpdev.swapi.dtos.PersonDTO;
import fr.kevpdev.swapi.dtos.SearchResultDTO;
import fr.kevpdev.swapi.services.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {

    private final WebClient webClient;

    @Value("${swapi.url}")
    private String swapiUri;

    public PersonServiceImpl(WebClient.Builder client) {
        this.webClient = client.baseUrl("https://swapi.dev/api/people").build();
    }


    @Override
    /**
     * Retrives a person by ID
     * @param id person ID
     * @return a Mono containing the PersonDTO object if found or empty if not
     */
    public Mono<PersonDTO> getPersonById(Integer id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(PersonDTO.class);
    }

    /**
     * Retrives all people with pagination
     *
     * @param pageId Pagination number
     * @return a Mono containing the SearchResultDTO object containing a PersonDTO array
     */
    @Override
    public Mono<SearchResultDTO<PersonDTO>> getPeopleAll(Integer pageId) {

        return webClient.get()
                .uri("/?page={pageId}", pageId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SearchResultDTO<PersonDTO>>() {});
    }

    @Override
    public Mono<SearchResultDTO<PersonDTO>> getPersonByName(String name) {
        return webClient.get()
                .uri("/?search={name}", name)
                .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<SearchResultDTO<PersonDTO>>() {});
    }
}
