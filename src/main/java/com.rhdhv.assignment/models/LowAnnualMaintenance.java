package com.rhdhv.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model to specifically contain parameters needed for calculating low annual maintenance cost for
 * cars
 */
@Document
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class LowAnnualMaintenance extends Deal implements Serializable {

  private static final long serialVersionUID = 2657458036911118940L;

  @JsonProperty(value = "dealType", required = true)
  @ApiModelProperty(example = "lowAnnualMaintenance", notes = "dealType")
  private String dealType;

  @JsonProperty(value = "fuelRate", required = true)
  @ApiModelProperty(example = "0.9", notes = "Fuel rate")
  @NotNull
  private float fuelRate;

  @JsonProperty(value = "travelDistance", required = true)
  @ApiModelProperty(example = "250", notes = "Expected disstance the user wants to travel each month")
  @NotNull
  private int travelDistance;

}
