package com.rhdhv.assignment.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CarDealerServiceIntegrationTest {

  @Autowired
  private ICarDealerService carDealerService;

  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  public void testCreateCars() {
    Car expectedRecord = Car.builder().brand("Honda").model("Cr-V").version("c").yearOfRelease(2019)
        .price(1234).fuelConsumption(0).maintenanceCost(1).build();
    Car actualRecord = carDealerService.save(expectedRecord);
    assertNotNull(actualRecord);
    assertEquals(expectedRecord.getBrand(), actualRecord.getBrand());
  }

  @Test
  @DirtiesContext(methodMode = MethodMode.AFTER_METHOD)
  public void testCreateCarsOnlyMandatoryFields() {
    Car expectedRecord = Car.builder().brand("Honda").model("Cr-V").version("c").yearOfRelease(2019)
        .price(1234).build();
    Car actualRecord = carDealerService.save(expectedRecord);
    assertNotNull(actualRecord);
    assertEquals(expectedRecord.getBrand(), actualRecord.getBrand());
  }

  @Test
  public void testGetCars() {
    SearchRequest searchRequest = SearchRequest.builder().build();
    List<Car> cars = carDealerService.get(searchRequest);
    assertNotNull(cars);
    assertEquals(7, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
      assertNotNull(car.getBrand());
      assertNotNull(car.getModel());
    });
  }

  @Test
  public void testSearchCarsWithMake() {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.eq).value("Honda")
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    cars.forEach(car -> {
      assertNotNull(car);
      assertNotNull(car.getBrand());
      assertEquals("Honda", car.getBrand());
      assertNotEquals("Hyundai", car.getBrand());
      assertNotEquals("Audi", car.getBrand());
    });
  }

  @Test
  public void testSearchCarsWithMakeStartsWithComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.sw).value("H")
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(4, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
      assertNotNull(car.getBrand());
    });
  }

  @Test
  public void testSearchCarsWithMakeNotEqualsComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.ne).value("Audi")
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(4, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
      assertNotNull(car.getBrand());
      assertNotEquals("Audi", car.getBrand());
    });
  }

  @Test
  public void testSearchCarsWithMakeEndsWithComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.ew).value("i")
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(4, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
      assertNotNull(car.getBrand());
      assertNotEquals("Honda", car.getBrand());
    });
  }

  @Test
  public void testSearchCarsWithMakeContainsComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("brand",
        StringSearch.builder().type("string").comparison(StringComparison.co).value("ud")
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(3, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
      assertNotNull(car.getBrand());
      assertEquals("Audi", car.getBrand());
      assertNotEquals("Honda", car.getBrand());
      assertNotEquals("Hyundai", car.getBrand());
    });
  }

  @Test
  public void testSearchCarsWithYearEqualsNoCarsComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.eq).value(2000)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(0, cars.size());

  }

  @Test
  public void testSearchCarsWithYearEqualsComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.eq).value(2020)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(3, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
      assertNotNull(car.getBrand());
      assertEquals(2020, car.getYearOfRelease());
    });
  }

  @Test
  public void testSearchCarsWithYearGreaterThanComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.gt).value(2019)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(3, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
      assertNotNull(car.getBrand());
      assertNotEquals(2019, car.getYearOfRelease());
    });
  }

  @Test
  public void testSearchCarsWithYearGreaterThanEqualComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.ge).value(2019)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(6, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
    });
  }

  @Test
  public void testSearchCarsWithYearLowerThanComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.lt).value(2019)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(1, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
    });
  }

  @Test
  public void testSearchCarsWithYearLowerThanEqualsComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.le).value(2019)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(4, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
    });
  }

  @Test
  public void testSearchCarsWithYearBetweenComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.bt).value(2019)
            .toValue(2020)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(6, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
    });
  }

  @Test
  public void testSearchCarsWithYearNotEqualComparison() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.ne).value(2019)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).build());
    assertNotNull(cars);
    assertEquals(4, cars.size());
    cars.forEach(car -> {
      assertNotNull(car);
    });
  }

  @Test
  public void testSearchCarsSortByBrandAsc() {
    Map<String, Search> map = new HashMap<>();
    SortModel sort = SortModel.builder().field("brand").sortType(SortType.asc).build();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.eq).value(2019)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).sort(sort).build());
    assertNotNull(cars);
    assertEquals(3, cars.size());
    assertEquals("Audi", cars.get(0).getBrand());
    assertEquals("Honda", cars.get(1).getBrand());
    assertEquals("Hyundai", cars.get(2).getBrand());
  }

  @Test
  public void testSearchCarsSortByBrandDesc() {
    Map<String, Search> map = new HashMap<>();
    SortModel sort = SortModel.builder().field("brand").sortType(SortType.desc).build();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.eq).value(2019)
            .build());
    List<Car> cars = carDealerService.get(SearchRequest.builder().search(map).sort(sort).build());
    assertNotNull(cars);
    assertEquals(3, cars.size());
    assertEquals("Hyundai", cars.get(0).getBrand());
    assertEquals("Honda", cars.get(1).getBrand());
    assertEquals("Audi", cars.get(2).getBrand());
  }


  @Test
  public void testGetDealByParameters() {
    Map<String, Search> map = new HashMap<>();
    map.put("yearOfRelease",
        NumberSearch.builder().type("number").comparison(NumberComparison.eq).value(2019)
            .build());
    DealRequest request = DealRequest.builder()
        .searchRequest(SearchRequest.builder().search(map).build()).deal(
            LowAnnualMaintenance.builder().dealType("lowAnnualMaintenance").fuelRate(10.0f)
                .travelDistance(250).build()).build();
    List<Car> cars = carDealerService.getCarsForDeal(request);
    assertNotNull(cars);
    assertEquals(3, cars.size());
    assertEquals(10, cars.get(0).getFuelConsumption());
    assertEquals(1000, cars.get(0).getMaintenanceCost());
    assertEquals(20000, cars.get(0).getPrice());
  }
}