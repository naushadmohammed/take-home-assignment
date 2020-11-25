package com.rhdhv.assignment.utils;

import static com.rhdhv.assignment.utils.QueryUtils.addNumberCriteria;
import static com.rhdhv.assignment.utils.QueryUtils.addStringCriteria;

import com.rhdhv.assignment.models.NumberSearch;
import com.rhdhv.assignment.models.Search;
import com.rhdhv.assignment.models.SortModel;
import com.rhdhv.assignment.models.StringSearch;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class QueryBuilder {

  private final Query query;

  public QueryBuilder() {
    this.query = new Query();
  }

  public Query getQuery() {
    return query;
  }

  public QueryBuilder withSearch(Optional<Map<String, Search>> search) {
    final List<Criteria> criteria = new ArrayList<>();
    if (search.isPresent()) {
      search.get().forEach((k, v) -> addCriteria(k, v, criteria));
    }

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

  private void addCriteria(String field, Search search, List<Criteria> criteria) {
    if (search instanceof StringSearch) {
      addStringCriteria(field, (StringSearch) search, criteria);
    }
    if (search instanceof NumberSearch) {
      addNumberCriteria(field, (NumberSearch) search, criteria);
    }
  }
}
