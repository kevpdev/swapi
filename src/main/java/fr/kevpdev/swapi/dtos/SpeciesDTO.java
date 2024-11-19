package fr.kevpdev.swapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SpeciesDTO {
    private String name;
    private String classification;
    private String designation;

    @JsonProperty("average_height")
    private String averageHeight;

    @JsonProperty("skin_colors")
    private String skinColors;

    @JsonProperty("hair_colors")
    private String hairColors;

    @JsonProperty("eye_colors")
    private String eyeColors;

    @JsonProperty("average_lifespan")
    private String averageLifespan;

    private String homeworld;
    private String language;
    private List<String> people;
    private List<String> films;

    private String created;
    private String edited;
    private String url;
}