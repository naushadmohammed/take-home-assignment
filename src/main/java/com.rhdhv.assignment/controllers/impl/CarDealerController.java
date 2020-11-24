package com.rhdhv.assignment.controllers.impl;

import com.rhdhv.assignment.controllers.ICarDealerController;
import com.rhdhv.assignment.models.Car;
import com.rhdhv.assignment.models.Search;
import com.rhdhv.assignment.services.ICarDealerService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Application Rest Controller Implementation.
 */
@RestController
@RequestMapping(value = "/cars")
@Validated
@RequiredArgsConstructor
@ResponseBody
public class CarDealerController implements ICarDealerController {

  private final ICarDealerService carDealerService;

  /**
   * {@inheritDoc}
   *
   * @return {@link ResponseEntity}
   */
  @Override
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Car> createCar(Car car) {
    System.out.println(car.toString());
    return new ResponseEntity<>(carDealerService.save(car),
        HttpStatus.CREATED);
  }

  /**
   * {@inheritDoc}
   *
   * @return {@link ResponseEntity}
   */
  @Override
  @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<Car>> getCars(Map<String, Search> search) {
    System.out.println(search);
    return new ResponseEntity<>(carDealerService.get(search), HttpStatus.OK);
  }
}