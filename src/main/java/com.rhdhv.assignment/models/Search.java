package com.rhdhv.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;

/**
 * Abstract class to be implemented using Polymorphic Json conversion
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@Type(value = NumberSearch.class, name = "number"),
    @Type(value = StringSearch.class, name = "string")})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Search implements Serializable {

  private static final long serialVersionUID = -8747945807172063110L;
}
