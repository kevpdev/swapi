package fr.kevpdev.swapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PersonDTO {
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("eye_color")
    private String eyeColor;
    private List<String> films;
    private String gender;
    @JsonProperty("hair_color")
    private String hairColor;
    private String height;
    private String homeworld;
    private String mass;
    private String name;
    @JsonProperty("skin_color")
    private String skinColor;
    private String created;
    private String edited;
    private List<String> species;
    private List<String> starships;
    private String url;
    private List<String> vehicles;

    /* lists with data */
    private List<FilmDTO> filmsDTO;
    private List<StarshipDTO> starshipsDTO;
    private List<VehicleDTO> vehiclesDTO;

}
