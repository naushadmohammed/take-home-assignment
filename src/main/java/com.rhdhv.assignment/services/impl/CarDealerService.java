package com.rhdhv.assignment.services.impl;

import com.rhdhv.assignment.models.Car;
import com.rhdhv.assignment.models.SearchRequest;
import com.rhdhv.assignment.repositories.CarRepository;
import com.rhdhv.assignment.services.ICarDealerService;
import com.rhdhv.assignment.utils.QueryBuilder;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Service Layer Implementation for Car Dealer Service
 */
@Service
@RequiredArgsConstructor
public class CarDealerService implements ICarDealerService {

  private final CarRepository carRepository;

  /**
   * {@inheritDoc}
   */
  @Override
  public Car save(Car car) {
    return carRepository.save(car);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Car> get(SearchRequest search) {
    Query query = new QueryBuilder().withSearch(Optional.ofNullable(search.getSearch()))
        .order(Optional.ofNullable(search.getSort())).getQuery();
    return carRepository.find(query);
  }


}
