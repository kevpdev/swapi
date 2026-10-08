package fr.kevpdev.swapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanetDTO {
  private String name;

  @JsonProperty("rotation_period")
  private String rotationPeriod;

  @JsonProperty("orbital_period")
  private String orbitalPeriod;

  private String diameter;
  private String climate;
  private String gravity;
  private String terrain;

  @JsonProperty("surface_water")
  private String surfaceWater;

  private String population;
  private List<String> residents;
  private List<String> films;

  private String created;
  private String edited;
  private String url;
}
