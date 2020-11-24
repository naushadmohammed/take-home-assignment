package com.rhdhv.assignment.utils;

import com.rhdhv.assignment.models.NumberSearch;
import com.rhdhv.assignment.models.StringSearch;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;

/**
 * Utility class with various helper methods
 */
public class Utils {

  /**
   * Static method to provide addition of criteria based on various comparison for Numeric data
   *
   * @param field    field to be compared in document
   * @param search   {@link NumberSearch} contains the details for comparison
   * @param criteria {@link List<Criteria>} which contains all the critera
   */
  public static void addNumberCriteria(String field, NumberSearch search, List<Criteria> criteria) {
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

  /**
   * Static method to provide addition of criteria based on various comparison for String data
   *
   * @param field    field to be compared in document
   * @param search   {@link StringSearch} contains the details for comparison
   * @param criteria {@link List<Criteria>} which contains all the critera
   */
  public static void addStringCriteria(String field, StringSearch search, List<Criteria> criteria) {
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
