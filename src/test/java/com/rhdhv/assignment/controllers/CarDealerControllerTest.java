package com.rhdhv.assignment.controllers;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhdhv.assignment.controllers.impl.CarDealerController;
import com.rhdhv.assignment.models.Car;
import com.rhdhv.assignment.models.DealRequest;
import com.rhdhv.assignment.models.LowAnnualMaintenance;
import com.rhdhv.assignment.models.NumberSearch;
import com.rhdhv.assignment.models.NumberSearch.NumberComparison;
import com.rhdhv.assignment.models.Search;
import com.rhdhv.assignment.models.SearchRequest;
import com.rhdhv.assignment.models.SortModel;
import com.rhdhv.assignment.models.SortModel.SortType;
import com.rhdhv.assignment.models.StringSearch;
import com.rhdhv.assignment.models.StringSearch.StringComparison;
import com.rhdhv.assignment.services.ICarDealerService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@WebMvcTest(controllers = CarDealerController.class)
public class CarDealerControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ICarDealerService carDealerService;

  @Test
  public void testWhenSearchAllCarsThen200OK() throws Exception {
    mockMvc.perform(post("/cars/search").contentType("application/json"))
        .andExpect(status().isOk());
  }

  @Test
  public void testWhenSearchCarsByYearThen200OK() throws Exception {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.eq).value(2000).build());
    mockMvc.perform(post("/cars/search")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(SearchRequest.builder().search(map).build())))
        .andExpect(status().isOk());
  }

  @Test
  public void testWhenSearchCarsByMakeThen200OK() throws Exception {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.eq).value("Honda")
            .build());
    mockMvc.perform(post("/cars/search")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(SearchRequest.builder().search(map).build())))
        .andExpect(status().isOk());
  }

  @Test
  public void testWhenDealrequestThenReturnCarsWithFewParameters() throws Exception {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.eq).value(2019)
            .build());
    DealRequest request = DealRequest.builder()
        .searchRequest(SearchRequest.builder().search(map).build()).deal(
            LowAnnualMaintenance.builder().dealType("lowAnnualMaintenance").fuelRate(10.0f)
                .travelDistance(250).build()).build();
    List<Car> cars = new ArrayList<>();
    cars.add(Car.builder().brand("Honda").model("Civic").maintenanceCost(100).fuelConsumption(1)
        .price(10000).build());
    String carValue = objectMapper.writeValueAsString(cars);
    when(carDealerService.getCarsForDeal(request)).thenReturn(cars);
    String result = mockMvc.perform(post("/cars/deal")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    assertThat(result, anyOf(containsString("maintenanceCost")));
    assertFalse(result.contains("brand"));
  }

  @Test
  public void testWhenSearchCarsNoTypeThen400BADREQUEST() throws Exception {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().comparison(StringComparison.eq).value("Honda")
            .build());
    SearchRequest sr = SearchRequest.builder().search(map).build();
    String om = objectMapper.writeValueAsString(sr);
    mockMvc.perform(post("/cars/search")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(om))).andExpect(status().isBadRequest());
  }

  @Test
  public void testWhenSearchCarsNoValueThen400BADREQUEST() throws Exception {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.eq)
            .build());
    SearchRequest sr = SearchRequest.builder().search(map).build();
    String om = objectMapper.writeValueAsString(sr);
    mockMvc.perform(post("/cars/search")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(om))).andExpect(status().isBadRequest());
  }

  @Test
  public void testWhenSearchCarsNoComparisonThen400BADREQUEST() throws Exception {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").value("abc")
            .build());
    SearchRequest sr = SearchRequest.builder().search(map).build();
    String om = objectMapper.writeValueAsString(sr);
    mockMvc.perform(post("/cars/search")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(om))).andExpect(status().isBadRequest());
  }

  @Test
  public void testWhenSearchCarsByMakeAndYearThen200OK() throws Exception {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.eq).value("Honda")
            .build());
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.eq).value(2000).build());

    mockMvc.perform(post("/cars/search")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(SearchRequest.builder().search(map).build())))
        .andExpect(status().isOk());
  }

  @Test
  public void testWhenSearchCarsSortOnly200OK() throws Exception {
    SortModel sort = SortModel.builder().field("brand").sortType(SortType.asc).build();
    mockMvc.perform(post("/cars/search")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(SearchRequest.builder().sort(sort).build())))
        .andExpect(status().isOk());
  }

  @Test
  public void testWhenSearchCarsByMakeAndSortThen200OK() throws Exception {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.eq).value("Honda")
            .build());
    SortModel sort = SortModel.builder().field("brand").sortType(SortType.asc).build();
    mockMvc.perform(post("/cars/search")
        .contentType("application/json")
        .content(objectMapper
            .writeValueAsString(SearchRequest.builder().search(map).sort(sort).build())))
        .andExpect(status().isOk());
  }

  @Test
  public void testWhenWrongAPICallThen404NotFound() throws Exception {
    mockMvc.perform(get("/cars/api?brand=Honda&yearOfRelease=2000"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testWhenPostCarsCallThen201CREATED() throws Exception {
    Car expectedRecord = Car.builder().brand("Honda").model("Cr-V").version("c").yearOfRelease(2019)
        .price(1234).fuelConsumption(0).maintenanceCost(1).build();
    mockMvc.perform(post("/cars")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(expectedRecord)))
        .andExpect(status().isCreated());
  }

  @Test
  public void testWhenPostCarsCallOnlyMandatoryFieldsThen200CREATED() throws Exception {
    Car expectedRecord = Car.builder().brand("Honda").model("Cr-V").version("c").yearOfRelease(2019)
        .price(1234).build();
    mockMvc.perform(post("/cars")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(expectedRecord)))
        .andExpect(status().isCreated());
  }

  @Test
  public void testWhenPostCarsCallMissingMandatoryFieldThen400BADREQUEST() throws Exception {
    Car expectedRecord = Car.builder().model("Cr-V").version("c").yearOfRelease(2019).price(1234)
        .build();
    mockMvc.perform(post("/cars")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(expectedRecord)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void testWhenPostCarsCallEmptyMandatoryFieldThen400BADREQUEST() throws Exception {
    Car expectedRecord = Car.builder().brand("").model("Cr-V").version("c").yearOfRelease(2019)
        .price(1234).build();
    mockMvc.perform(post("/cars")
        .contentType("application/json")
        .content(objectMapper.writeValueAsString(expectedRecord)))
        .andExpect(status().isBadRequest());
  }


  @Test
  public void testWhenPost1CarsCallThen201CREATED() throws Exception {
    Car expectedRecord = Car.builder().brand("Honda").model("Cr-V").version("c").yearOfRelease(2019)
        .price(1234).fuelConsumption(0).maintenanceCost(1).build();
    mockMvc.perform(post("/cars/search")
        .contentType("application/json"))
        .andExpect(status().isOk());
  }

}
