package com.rhdhv.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

/**
 * Model containing search parameters and associated sorting.
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class SearchRequest implements Serializable {

  private static final long serialVersionUID = -1003681789701121374L;

  @JsonProperty(value = "search")
  @ApiModelProperty(example = "{\n"
      + "    \"brand\": {\n"
      + "        \"type\": \"string\",\n"
      + "        \"value\": \"Honda\",\n"
      + "        \"comparison\": \"eq\"\n"
      + "    },\n"
      + "    \"yearOfRelease\": {\n"
      + "        \"type\": \"number\",\n"
      + "        \"value\": 2020,\n"
      + "        \"comparison\": \"eq\",\n"
      + "        \"toValue\": null\n"
      + "    }\n"
      + "}", notes = "Search Object that would contain all the necessary "
      + "parameters for a search to perform correctly")
  private Map<String, Search> search;

  @JsonProperty(value = "sort")
  @ApiModelProperty(example = "{\n"
      + "    \"field\": \"model\",\n"
      + "    \"sortType\": \"asc\"\n"
      + "}", notes = "Type using which sort needs to be done")
  private SortModel sort;
}
