# Spring Rest JWT Example

This project is an example of how we can protect our API RESTful endpoints using JWT and how we can manage the
authorization on each resource.

## About

The main goal of this project is to show how we can implement the JWT and manage the authorizations with ROLE's.

## Technologies

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [JWT](https://jwt.io)
- [Lombok](https://projectlombok.org)
- [H2 Database](https://www.h2database.com/html/quickstart.html)

## Features

- Create token endpoint
- Resource authorization by ROLE's

## Built With

- [Maven](https://maven.apache.org/index.html)
- [Spring Initializr](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.3&packaging=jar&jvmVersion=11&groupId=com.example&artifactId=jwt&name=SpringRestJwt&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.jwt&dependencies=web,data-jpa,security,h2,devtools,lombok)

## How can i test my endpoints?

First, open your preference code editor and run the following code below:

```
mvn clean install
```

After the maven downloaded all dependencies, run the SpringRestJwtApplication.class.

For tests cases, you can check the API endpoints with Postman.

- [Postman Collection](https://www.getpostman.com/collections/51357a340e4d56d97f11)