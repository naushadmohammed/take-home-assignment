package com.rhdhv.assignment.repositories;

import com.rhdhv.assignment.models.Car;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository layer implementation for mongodb
 */
@Repository
@RequiredArgsConstructor
public class CarRepository {

  private final MongoTemplate mongoTemplate;

  /**
   * Repository method to persists {@link Car} model
   *
   * @param car {@link Car} model
   * @return {@link Car}
   */
  public Car save(Car car) {
    return mongoTemplate.save(car);
  }

  /**
   * Repository method to search documents
   *
   * @param query {@link Query} Query build on criterias to be used to search documents
   * @return {@link List<Car>} List of cars returned to be sent to the consumer
   */
  public List<Car> find(Query query) {
    return mongoTemplate.find(query, Car.class);
  }

  /**
   * Repository method to get all the cars for a particular deal
   *
   * @param aggregation {@link Aggregation} to used to filter and perform calculation on the fields
   * @return {@link List<Car>} List of cars returned to be sent to the consumer
   */
  public List<Car> getCarsForDeal(Aggregation aggregation) {
    return mongoTemplate.aggregate(
        aggregation, "car", Car.class).getMappedResults();
  }
}
