package com.rhdhv.assignment.utils;

import com.rhdhv.assignment.models.NumberSearch;
import com.rhdhv.assignment.models.Search;
import com.rhdhv.assignment.models.StringSearch;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * Builder class to assist in building Criteria which will be used by mongo to create criterias used
 * in filtering searches
 */
public class CriteriaBuilder {

  final List<Criteria> criterias;

  public CriteriaBuilder() {
    criterias = new ArrayList<>();
  }

  public List<Criteria> createCritera(Optional<Map<String, Search>> search) {
    if (search.isPresent()) {
      search.get().forEach((k, v) -> addCriteria(k, v, criterias));
    }
    return criterias;
  }

  private void addCriteria(String field, Search search, List<Criteria> criteria) {
    if (search instanceof StringSearch) {
      addStringCriteria(field, (StringSearch) search, criteria);
    }
    if (search instanceof NumberSearch) {
      addNumberCriteria(field, (NumberSearch) search, criteria);
    }
  }

  private void addNumberCriteria(String field, NumberSearch search, List<Criteria> criteria) {
    switch (search.getComparison()) {
      case eq:
        criteria.add(Criteria.where(field).is(search.getValue()));
        break;
      case ne:
        criteria.add(Criteria.where(field).ne(search.getValue()));
        break;
      case bt:
        criteria.add(Criteria.where(field).gte(search.getValue()).lte(search.getToValue()));
        break;
      case gt:
        criteria.add(Criteria.where(field).gt(search.getValue()));
        break;
      case lt:
        criteria.add(Criteria.where(field).lt(search.getValue()));
        break;
      case ge:
        criteria.add(Criteria.where(field).gte(search.getValue()));
        break;
      case le:
        criteria.add(Criteria.where(field).lte(search.getValue()));
        break;
    }
  }

  private void addStringCriteria(String field, StringSearch search, List<Criteria> criteria) {
    switch (search.getComparison()) {
      case eq:
        criteria.add(Criteria.where(field).is(search.getValue()));
        break;
      case ne:
        criteria.add(Criteria.where(field).ne(search.getValue()));
        break;
      case sw:
        criteria.add(Criteria.where(field).regex("^" + search.getValue()));
        break;
      case ew:
        criteria.add(Criteria.where(field).regex(search.getValue() + "$"));
        break;
      case co:
        criteria.add(Criteria.where(field).regex(".*" + search.getValue() + ".*", "i"));
        break;
    }
  }
}
