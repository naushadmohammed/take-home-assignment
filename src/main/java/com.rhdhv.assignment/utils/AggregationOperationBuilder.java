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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

  final List<AggregationOperation> aggregationOperationList;
  final private String FORMULA_ALIAS = "calc";

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
    createMatchOperations(Optional.ofNullable(dealRequest.getSearchRequest()));
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
        Arrays.stream(Car.class.getDeclaredFields()).map(field -> field.getName())
            .toArray(String[]::new))
        .and(Add.valueOf("maintenanceCost")
            .add(Divide.valueOf(
                Multiply.valueOf(deal.getTravelDistance()).multiplyBy(12)
                    .multiplyBy(deal.getFuelRate()))
                .divideBy("fuelConsumption")))
        .as(FORMULA_ALIAS));
  }

  private void createMatchOperations(Optional<SearchRequest> searchRequest) {
    if (searchRequest.isPresent()) {
      new CriteriaBuilder().createCritera(Optional.ofNullable(searchRequest.get().getSearch()))
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
