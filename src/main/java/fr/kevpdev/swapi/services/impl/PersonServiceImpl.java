package fr.kevpdev.swapi.services.impl;

import fr.kevpdev.swapi.dtos.*;
import fr.kevpdev.swapi.services.PersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final WebClient webClient;

    @Value("${swapi.url}")
    private String swapiUri;

    public PersonServiceImpl(WebClient.Builder client) {
        this.webClient = client.baseUrl("https://swapi.dev/api/people").build();
    }


    /**
     * Retrives a person by ID
     * @param id person ID
     * @return a Mono containing the PersonDTO object if found or empty if not
     */
    @Override
    public Mono<PersonDTO> getPersonById(Integer id) {
        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(PersonDTO.class)
                .flatMap(personDTO -> {

                    // Exemple pour la curiosité de récupération des DTO
                    // Mais je ne vais pas étendre car aucune indication des besoins du front et aussi moyen pour les perfs
                    // L'utilisation d'un cache serait cool pour eviter les appels inutiles
                    Mono<List<FilmDTO>> monoFilmsDTO = getResourcesByUrls(personDTO.getFilms(), FilmDTO.class);
                    Mono<List<StarshipDTO>> monoStarshipsDTO = getResourcesByUrls(personDTO.getStarships(), StarshipDTO.class);
                    Mono<List<VehicleDTO>> monoVehiclesDTO = getResourcesByUrls(personDTO.getStarships(), VehicleDTO.class);

                    return Mono.zip(monoFilmsDTO, monoStarshipsDTO,monoVehiclesDTO).map(data -> {
                        personDTO.setFilmsDTO(data.getT1());
                        personDTO.setStarshipsDTO(data.getT2());
                        personDTO.setVehiclesDTO(data.getT3());
                        return personDTO;
                    });
                });
    }

    /**
     * Retrives all people with pagination
     *
     * @param pageId Pagination number
     * @return a Mono containing the SearchResultDTO object containing a PersonDTO array
     */
    @Override
    public Mono<SearchResultDTO<PersonDTO>> getPeople(Integer pageId) {

        return webClient.get()
                .uri("/?page={pageId}", pageId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<SearchResultDTO<PersonDTO>>() {});
    }


    /**
     * Retrives all people by name
     * @param name person name
     * @return a Mono containing the PersonDTO object list if found or empty if not
     */
    @Override
    public Mono<SearchResultDTO<PersonDTO>> getPersonByName(String name) {
        return webClient.get()
                .uri("/?search={name}", name)
                .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<SearchResultDTO<PersonDTO>>() {});
    }

    /**
     * Retrieves DTO object list by hypermedia link
     * @param urls hypermedialink list
     * @param responseType DTO type
     * @return a Mono containing the Generic DTO object list if found or empty if not
     * @param <T>
     */
    public <T> Mono<List<T>> getResourcesByUrls(List<String> urls, Class<T> responseType) {
        return Flux.fromIterable(urls)
                .flatMap(url -> webClient.get()
                        .uri(URI.create(url))
                        .retrieve()
                        .bodyToMono(responseType))
                .collectList();
    }
}
