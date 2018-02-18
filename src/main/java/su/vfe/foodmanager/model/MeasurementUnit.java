package su.vfe.foodmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MeasurementUnit {
    @JsonProperty("кг")
    KILOGRAM,
    @JsonProperty("г")
    GRAM,
    @JsonProperty("л")
    LITER,
    @JsonProperty("мл")
    MILLILITER,
    @JsonProperty("шт")
    PIECE
}
