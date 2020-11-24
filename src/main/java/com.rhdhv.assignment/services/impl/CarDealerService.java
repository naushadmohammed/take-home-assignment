package com.rhdhv.assignment.services.impl;

import static com.rhdhv.assignment.utils.Utils.addNumberCriteria;
import static com.rhdhv.assignment.utils.Utils.addStringCriteria;

import com.rhdhv.assignment.models.Car;
import com.rhdhv.assignment.models.NumberSearch;
import com.rhdhv.assignment.models.Search;
import com.rhdhv.assignment.models.StringSearch;
import com.rhdhv.assignment.repositories.CarRepository;
import com.rhdhv.assignment.services.ICarDealerService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
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
  public List<Car> get(Map<String, Search> search) {
    final Query query = new Query();
    final List<Criteria> criteria = new ArrayList<>();
    search.forEach((k, v) -> addCriteria(k, v, criteria));

    if (!criteria.isEmpty()) {
      query
          .addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
    }
    return carRepository.find(query);
  }

  private void addCriteria(String field, Search search, List<Criteria> criteria) {
    if (search instanceof StringSearch) {
      addStringCriteria(field, (StringSearch) search, criteria);
    }
    if (search instanceof NumberSearch) {
      addNumberCriteria(field, (NumberSearch) search, criteria);
    }
  }

}
