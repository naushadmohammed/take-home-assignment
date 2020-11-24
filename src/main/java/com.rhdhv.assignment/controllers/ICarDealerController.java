package com.rhdhv.assignment.controllers;

import com.rhdhv.assignment.exceptions.error.ApplicationCustomError;
import com.rhdhv.assignment.models.Car;
import com.rhdhv.assignment.models.Search;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Application Rest Controller Contract Definition
 */
@Api(value = "Car Dealer Controller")
public interface ICarDealerController {

  /**
   * Post Request to create a new {@link Car} in database and returns the created Car details
   *
   * @param car {@link Car} representing the Stock Trade .
   * @return {@link ResponseEntity} with wrapped status code and  {@link Car}
   */
  @ApiOperation(value = "Creates a new Car with the details provided and returns the created car to the user")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "New Car was created successfully",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = Car.class),
      @ApiResponse(code = 400, message = "Invalid or malformed Request Body",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApplicationCustomError.class),
      @ApiResponse(code = 500,
          message = "An unexpected error has occurred. The error has been logged and is being investigated")})
  ResponseEntity<Car> createCar(@Valid @RequestBody Car car);


  /**
   * Retrieve {@link List} of {@link Car} for the given search criteria.
   *
   * @param search {@link Map<String, Search>} representing the search criteria.
   * @return {@link ResponseEntity} with wrapped status code and {@link List} of {@link Car}
   */
  @ApiOperation(value = "Searches Stock for the given input search criteria")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Search was successful for the given input search critera.",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = Car.class, responseContainer = "List"),
      @ApiResponse(code = 400, message = "Invalid or malformed query parameter.",
          examples = @Example(
              value = @ExampleProperty(mediaType = MediaType.APPLICATION_JSON_VALUE, value = "")),
          response = ApplicationCustomError.class),
      @ApiResponse(code = 500,
          message = "An unexpected error has occurred. The error has been logged and is being investigated")})
  ResponseEntity<List<Car>> getCars(
      @Valid @RequestBody(required = false) Map<String, Search> search);
}

