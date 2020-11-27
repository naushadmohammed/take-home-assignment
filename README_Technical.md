# Car Dealer Service

Car Dealer API allows the dealer/user to add new cars, and search the inventory based on several parameters and different criterias.
The API also allows user to get a particular recommendation based on a pre-defined deal, which is configured in the application.

## Getting Started
Below instructions will get you a copy of up and running project on your local machine for development and testing purposes.

### Technologies Used
* [Spring Boot](https://spring.io/projects/spring-boot): The Spring Boot Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application, but there are extensions for building web applications on top of the Java EE platform.
* [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java
* [MongoDB](https://www.mongodb.com/) - A Document based distributed database. for this project, we are using embedded mongodb, so we don't need to install anything. 
* [Swagger](https://swagger.io/docs/) - Interactive API documentation
* [Lombok](https://projectlombok.org/) - Java library that automatically plugs into your editor and build tools
* [Maven](https://maven.apache.org/) - Maven 3.6.1 


## Architecture Approach
Backend - Spring Boot : 
Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
Embedded Tomcat provides a quick way to run our applications on any servers without prior setup or installation of any webservers.
Sring boot makes it easier to scale up the application vertically on any given cloud platforms.

Database - Mongo DB : 
MongoDB is a general purpose, document-based, distributed database built for the cloud era.
Considering the load on Car dealer application, search feature and complex suggestion based on various parameters would be used most. 
Keeping this in mind Mongo DB stands out the best as it provides a faster reading capability compared to various databases, 
and can also handle complex query which can be run directly on a database.
 

## Steps to run the Media Service API

### Pre-requisite 
- Java 8, Maven are installed.
- IDE used : Intellij with lombok plugin installed
### For Backend
- Step 1: Checkout the above mentioned application and navigate to root folder.
- Step 2: Use `mvn clean install` to build the application.
- Step 3: Use `mvn spring-boot:run` to start the application using default profile.

### Non Functional Requirements
- Rest API Documentation is available at : 
`http://localhost:8080/rhdhv/swagger-ui.html` 

- HealthCheck is available at :  
`http://localhost:8080/rhdhv/healthcheck.html`

### Sample Request and Response
* To Create Cars, please find below example request : POST `localhost:8080/rhdhv/cars`
```json
{
   "brand": "Honda",
   "fuelConsumption": 10,
   "maintenanceCost": 100,
   "model": "CR-V",
   "price": 11000,
   "version": 1,
   "yearOfRelease": 2019
 }
``` 
 
* To Search for Cars, please find below a sample search request : POST `localhost:8080/rhdhv/cars/search`
 
```json
{
  "search": {
    "brand": {  
      "type": "string",
      "value": "Honda",
      "comparison": "eq"
    },
    "yearOfRelease": {
      "type": "number",
      "value": 2020,
      "comparison": "eq"
    }
  },
  "sort": {
    "field": "fuelConsumption",
    "sortType": "desc"
  }
}
```

* To suggest a recommendation, please find below a sample search request : POST `localhost:8080/rhdhv/cars/deal`
```json
{
  "searchRequest": {
    "search": {
      "yearOfRelease": {
        "type": "number",
        "value": 2019,
        "comparison": "eq"
      }
    },
    "sort": null
  },
  "deal": {
    "dealType": "lowAnnualMaintenance",
    "fuelRate": 10,
    "travelDistance": 250
  }
}
```
### Authors
* **Naushad Mohammed** 