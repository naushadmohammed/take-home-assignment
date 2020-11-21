package com.rhdhv.assignment.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Application Web MVC Configuration overriding default MVC configuration
 */
@Configuration
@EnableSwagger2
@Profile(value = "!test")
public class ApplicationWebMvcConfiguration {

  /**
   * {@link Docket} bean definition to configure Swagger.
   *
   * @return {@link Docket}
   */
  @Bean
  public Docket generalAPIs() {
    return new Docket(DocumentationType.SWAGGER_2)
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.rhdhv.assignment.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("RoyalHaskoningDHV Car Dealer Application")
        .description(
            "This API allows the user to create Cars, search for a car and recommendation based on input.")
        .version("1.0.0").build();
  }

}
