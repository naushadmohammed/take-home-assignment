package com.rhdhv.assignment.utils;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

import com.rhdhv.assignment.models.Car;
import com.rhdhv.assignment.models.Deal;
import com.rhdhv.assignment.models.DealRequest;
import com.rhdhv.assignment.models.LowAnnualMaintenance;
import com.rhdhv.assignment.models.SearchRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Add;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Divide;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Multiply;

/**
 * Builder class to assist in building Aggregation Operations which will be used by mongo to perform
 * aggregation
 */
public class AggregationOperationBuilder {

  private static final String FORMULA_ALIAS = "calc";
  final List<AggregationOperation> aggregationOperationList;

  public AggregationOperationBuilder() {
    aggregationOperationList = new ArrayList<>();
  }

  /**
   * Helps in calling different layer of Aggregation Operations. Currently calling all the different
   * Aggregation stages from this method. We can enhance this to create builder methods.
   *
   * @param dealRequest {@link DealRequest}
   * @return
   */
  public AggregationOperationBuilder withDealRequest(DealRequest dealRequest) {
    createMatchOperations(dealRequest.getSearchRequest());
    createProjectionOperations(dealRequest.getDeal());
    createSortOperation();
    return this;
  }

  private void createLimitOperation() {
    this.aggregationOperationList.add(limit(3));
  }

  private void createSortOperation() {
    this.aggregationOperationList.add(sort(Sort.by(Direction.ASC, FORMULA_ALIAS)));
  }

  private void createProjectionOperations(Deal deal) {
    if (deal instanceof LowAnnualMaintenance) {
      createrLowAnnualMaintenanceProjection((LowAnnualMaintenance) deal);
    }
  }

  private void createrLowAnnualMaintenanceProjection(LowAnnualMaintenance deal) {
    this.aggregationOperationList.add(project(
        Arrays.stream(Car.class.getDeclaredFields()).map(Field::getName)
            .toArray(String[]::new))
        .and(Add.valueOf("maintenanceCost")
            .add(Divide.valueOf(
                Multiply.valueOf(deal.getTravelDistance()).multiplyBy(12)
                    .multiplyBy(deal.getFuelRate()))
                .divideBy("fuelConsumption")))
        .as(FORMULA_ALIAS));
  }

  private void createMatchOperations(SearchRequest searchRequest) {
    if (null != searchRequest) {
      new CriteriaBuilder().createCritera(searchRequest.getSearch())
          .forEach(criteria -> this.aggregationOperationList.add(match(criteria)));
    }

  }

  /**
   * Returns the list of all the AggregationOperation created
   *
   * @return
   */
  public List<AggregationOperation> getOperations() {
    return this.aggregationOperationList;
  }
}
