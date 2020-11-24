package com.rhdhv.assignment.configurations;

import com.rhdhv.assignment.models.Car;
import com.rhdhv.assignment.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuration for MongoDB to create car records during startup.
 */
@EnableMongoRepositories(basePackageClasses = CarRepository.class)
@Configuration
public class MongoDBConfig {

  //Added to create Cars so that it would be easier for creating searches and testing. Can be removed later and moved to test.
  @Bean
  CommandLineRunner commandLineRunner(CarRepository carRepository) {
    return strings -> {
      carRepository.save(Car.builder().brand("Honda").model("Cr-V").version("c").yearOfRelease(2019)
          .price(20000).fuelConsumption(10).maintenanceCost(1000).build());
      carRepository.save(Car.builder().brand("Honda").model("Cr-V").version("c").yearOfRelease(2020)
          .price(20500).fuelConsumption(10).maintenanceCost(1000).build());
      carRepository
          .save(Car.builder().brand("Honda").model("Accord").version("c").yearOfRelease(2020)
              .price(21000).fuelConsumption(15).maintenanceCost(1000).build());
      carRepository.save(Car.builder().brand("Audi").model("A5").version("5").yearOfRelease(2019)
          .price(30000).fuelConsumption(10).maintenanceCost(1500).build());
      carRepository.save(Car.builder().brand("Audi").model("A5").version("5").yearOfRelease(2020)
          .price(30000).fuelConsumption(10).maintenanceCost(1500).build());
      carRepository.save(Car.builder().brand("Audi").model("Q5").version("e").yearOfRelease(2018)
          .price(35000).fuelConsumption(9).maintenanceCost(1500).build());
      carRepository.save(Car.builder().brand("Hyundai").model("A2").version("e").yearOfRelease(2019)
          .price(35000).fuelConsumption(9).maintenanceCost(1500).build());
    };
  }

}