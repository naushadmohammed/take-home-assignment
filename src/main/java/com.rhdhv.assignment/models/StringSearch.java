package com.rhdhv.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StringSearch extends Search implements Serializable {

  private static final long serialVersionUID = -6344471237182063130L;

  @JsonProperty(value = "type", required = true)
  @ApiModelProperty(example = "string", notes = "send string if the type of search is text")
  private String type;

  @JsonProperty(value = "value", required = true)
  @ApiModelProperty(example = "Honda", notes = "String value to be compared")
  @NotNull
  private String value;

  @JsonProperty(value = "comparison", required = true)
  @ApiModelProperty(example = "eq", notes = "Use this comparison for various type of compare. For ex. Use eq to search for equal comparator")
  @NotNull
  private StringSearch.StringComparison comparison;

  /**
   * Enum to store various string comparators
   */
  public enum StringComparison {eq, ne, sw, ew, co}

}
