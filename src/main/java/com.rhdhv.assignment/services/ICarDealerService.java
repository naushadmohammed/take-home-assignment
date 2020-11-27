package com.rhdhv.assignment.services;

import com.rhdhv.assignment.models.Car;
import com.rhdhv.assignment.models.DealRequest;
import com.rhdhv.assignment.models.SearchRequest;
import java.util.List;

/**
 * Service layer contract for Car Dealer Service
 */
public interface ICarDealerService {

  /**
   * Allows to save the Car for a particular Model in database
   *
   * @param car {@link Car} model
   * @return {@link Car} car model
   */
  Car save(Car car);

  /**
   * Allows to search based on the stock id
   *
   * @param search {@link SearchRequest} search
   * @return {@link List<Car>} Variants of car based on search string
   */
  List<Car> get(SearchRequest search);

  /**
   * Allows to get cars based on a certain deal
   *
   * @param dealRequest {@link DealRequest} dealrequest
   * @return {@link List<Car>} Variants of car based on a certain deal
   */
  List<Car> getCarsForDeal(DealRequest dealRequest);
}
