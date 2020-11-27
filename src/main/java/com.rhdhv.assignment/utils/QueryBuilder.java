package com.rhdhv.assignment.utils;

import com.rhdhv.assignment.models.Search;
import com.rhdhv.assignment.models.SortModel;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;

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

  public QueryBuilder withSearch(Map<String, Search> search) {
    final List<Criteria> criteria = new CriteriaBuilder().createCritera(search);
    if (!CollectionUtils.isEmpty(criteria)) {
      query
          .addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));
    }
    return this;
  }

  public QueryBuilder order(SortModel sort) {
    if (null == sort) {
      return this;
    }
    switch (sort.getSortType()) {
      case asc:
        query.with(Sort.by(Direction.ASC, sort.getField()));
        break;
      case desc:
        query.with(Sort.by(Direction.DESC, sort.getField()));
        break;
    }
    return this;

  }

}
