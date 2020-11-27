package com.rhdhv.assignment;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarDealerApplicationTests {

  @Test
  public void contextLoads() {
    System.out.println("contextLoads");
  }

}