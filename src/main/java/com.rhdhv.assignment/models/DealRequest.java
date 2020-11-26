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
 * DealRequest model to include the deal parameters and Search Request parameters. For ex. :
 * <pre>{@code
 * {
 *   "searchRequest": {
 *     "search": {
 *       "yearOfRelease": {
 *         "type": "number",
 *         "value": 2019,
 *         "toValue": 0,
 *         "comparison": "eq"
 *       }
 *     },
 *     "sort": null
 *   },
 *   "deal": {
 *     "dealType": "lowAnnualMaintenance",
 *     "fuelRate": 10,
 *     "travelDistance": 250
 *   }
 * }}</pre>
 */
@Document
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class DealRequest implements Serializable {

  private static final long serialVersionUID = -7966260516548500298L;
  @JsonProperty(value = "searchRequest")
  @ApiModelProperty(example = "{\n"
      + "    \"searchRequest\": {\n"
      + "        \"search\": {\n"
      + "            \"yearOfRelease\": {\n"
      + "                \"type\": \"number\",\n"
      + "                \"value\": 2019,\n"
      + "                \"toValue\": 0,\n"
      + "                \"comparison\": \"eq\"\n"
      + "            }\n"
      + "        },\n"
      + "        \"sort\": \"yearOfRelease\"\n"
      + "    }\n"
      + "}", notes = "dealType")
  private SearchRequest searchRequest;

  @JsonProperty(value = "deal", required = true)
  @ApiModelProperty(example = "{\n"
      + "      \"deal\": {\n"
      + "    \"dealType\": \"lowAnnualMaintenance\",\n"
      + "    \"fuelRate\": 10,\n"
      + "    \"travelDistance\": 250\n"
      + "  }\n"
      + "}", notes = "dealType")
  @NotNull
  private Deal deal;
}
