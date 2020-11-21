package com.rhdhv.assignment.exceptions.error;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Definition of Application Custom Error
 */
@Data
@ApiModel
public class ApplicationCustomError {

  @ApiModelProperty(notes = "HTTP status code.")
  private final Integer status;
  @ApiModelProperty(notes = "Error message.")
  private final String message;
}
