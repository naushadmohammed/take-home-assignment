package com.rhdhv.assignment.repositories;

import com.rhdhv.assignment.models.Car;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

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
}
