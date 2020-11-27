package com.rhdhv.assignment.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Abstract class to be implemented using Polymorphic Json conversion
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "dealType")
@JsonSubTypes({@Type(value = LowAnnualMaintenance.class, name = "lowAnnualMaintenance")})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deal {

}
