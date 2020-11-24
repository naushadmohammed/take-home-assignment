package com.rhdhv.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Car implements Serializable {

  private static final long serialVersionUID = 4979456627974027781L;

  @JsonProperty(value = "brand", required = true)
  @ApiModelProperty(example = "Honda", notes = "Brand and Make of the car.")
  @NotNull
  @NotEmpty
  private String brand;

  @JsonProperty(value = "model", required = true)
  @ApiModelProperty(example = "CR-V", notes = "Model of the car")
  @NotNull
  @NotEmpty
  private String model;

  @JsonProperty(value = "version", required = true)
  @ApiModelProperty(example = "1", notes = "Version of the car")
  @NotNull
  @NotEmpty
  private String version;

  @JsonProperty(value = "yearOfRelease", required = true)
  @ApiModelProperty(example = "2019", notes = "Year of release of the car")
  @NotNull
  //@Field("year_of_release")
  private int yearOfRelease;

  @JsonProperty(value = "price", required = true)
  @ApiModelProperty(example = "11000", notes = "Sales Price of the car")
  @NotNull
  private int price;

  @JsonProperty(value = "fuelConsumption")
  @ApiModelProperty(example = "10", notes = "Fuel Consumption or mileage of the car, for ex : 10 km/l")
  @Field("fuel_consumption")
  private int fuelConsumption;

  @JsonProperty(value = "maintenanceCost")
  @ApiModelProperty(example = "100", notes = "Annual maintenance cost of the car")
  @Field("maintenance_cost")
  private int maintenanceCost;

}
