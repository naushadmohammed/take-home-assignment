package com.rhdhv.assignment.utils;

import com.rhdhv.assignment.models.Search;
import com.rhdhv.assignment.models.SortModel;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Builder class to assist in building Query which will be used by mongo to create create query
 * searches
 */
public class QueryBuilder {

  private final Query query;

  public QueryBuilder() {
    this.query = new Query();
  }

  public Query getQuery() {
    return query;
  }

  public QueryBuilder withSearch(Optional<Map<String, Search>> search) {
    final List<Criteria> criteria = new CriteriaBuilder().createCritera(search);
    if (!criteria.isEmpty()) {
      query
          .addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
    }
    return this;
  }

  public QueryBuilder order(Optional<SortModel> sort) {
    if (!sort.isPresent()) {
      return this;
    }
    switch (sort.get().getSortType()) {
      case asc:
        query.with(Sort.by(Direction.ASC, sort.get().getField()));
        break;
      case desc:
        query.with(Sort.by(Direction.DESC, sort.get().getField()));
        break;
    }
    return this;

  }

}
