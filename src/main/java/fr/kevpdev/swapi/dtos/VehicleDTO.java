package fr.kevpdev.swapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VehicleDTO {
    private String name;
    private String model;
    private String manufacturer;

    @JsonProperty("cost_in_credits")
    private String costInCredits;

    private String length;

    @JsonProperty("max_atmosphering_speed")
    private String maxAtmospheringSpeed;

    private String crew;
    private String passengers;

    @JsonProperty("cargo_capacity")
    private String cargoCapacity;

    private String consumables;

    @JsonProperty("vehicle_class")
    private String vehicleClass;

    private List<String> pilots;
    private List<String> films;

    private String created;
    private String edited;
    private String url;
}

