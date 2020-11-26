package com.rhdhv.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

/**
 * Model to store parameters to be used while searching numeric fields. Different values can be
 * passed for comparison. for ex. comparison : <b>eq</b> will result in a comparison with equalto
 * operator.
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class NumberSearch extends Search implements Serializable {

  private static final long serialVersionUID = 2945487960396122509L;

  @JsonProperty(value = "type", required = true)
  @ApiModelProperty(example = "number", notes = "send number if the type of search is numeric")
  private String type;

  @JsonProperty(value = "value", required = true)
  @ApiModelProperty(example = "1000", notes = "Numeric value to be compared")
  @NotNull
  private int value;

  @JsonProperty(value = "toValue")
  @ApiModelProperty(example = "2000", notes = "Numeric value to be used for between operators.")
  private int toValue;

  @JsonProperty(value = "comparison", required = true)
  @ApiModelProperty(example = "eq", notes = "Use this comparison for various type of compare. For ex. Use eq to search for equal comparator")
  @NotNull
  private NumberSearch.NumberComparison comparison;

  /**
   * Enum to store various Numeral comparators
   */
  public enum NumberComparison {eq, ne, lt, gt, bt, le, ge}

}
