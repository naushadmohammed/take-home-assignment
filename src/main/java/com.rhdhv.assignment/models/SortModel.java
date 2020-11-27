package com.rhdhv.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * SortModel to be used for sorting using a parameter
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortModel {

  @JsonProperty(value = "field")
  @ApiModelProperty(example = "Brand", notes = "Field on which sort needs to be done")
  private String field;

  @JsonProperty(value = "sortType")
  @ApiModelProperty(example = "asc", notes = "Type using which sort needs to be done")
  private SortModel.SortType sortType;

  /**
   * Enum to store Sort Types
   */
  public enum SortType {asc, desc}

}
