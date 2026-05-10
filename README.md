# RUWICH API

_Spring Boot application using Swagger UI and PostgreSQL database._

## Requirements

- Java 17+
- Maven
- PostgreSQL database

## Guide - How to get started

1. Configure database
   - Create a `.env` file to connect a postgreSQL database. We have used Neon for this API.
   - In your `.env` file type the following: </br> </br>
    DB_URL=<your_db></br>
      DB_USERNAME=<your_name></br>
      DB_PASSWORD=<your_password></br>
     
2. Database structure
   - Tables are created automatically via JPA/Hibernate.
   - Make sure you have in `application.properties`: </br> </br>
   spring.jpa.hibernate.ddl-auto=update </br>

## Authentication

This API is made using [JWT token](https://www.jwt.io/introduction#what-is-json-web-token) authentication. </br>
1. Create a user using `POST /auth/signup` </br>
2. Log in using `POST /auth/login` </br>
3. Copy the generated JWT token </br>
4. Paste the token to authorize at the top of the Swagger Interface </br>



## External Dependencies

- Spring Security
- Spring Data JPA
- Spring OpenAPI
- JWT


## API Documentation

Swagger UI:</br>
http://localhost:8080/swagger-ui/index.html </br>

OpenAPI docummentation:</br>
http://localhost:8080/v3/api-docs</br></br>




# PROJECT REQUIREMENTS

_What we have implemented so far based on the given mission statement._

## Data Requirements

- An anomaly has a unique identifier, a classification and a freeform description.
- An anomaly can be associated with any number of observations. Each observation includes a date, location, and a freeform description of the event or sighting.
- Spring Data is used to persist all data. Spring Data JPA is the industry standard for storing relational data but you may also choose to use e.g. Spring Data MongoDB or Spring Data Neo4J if you want to try out some NoSQL-type persistence provider.


## General API Requirements

- The API is implemented using Spring MVC.
- The API returns data in JSON format.
- The API endpoints are designed according to RESTful principles with regards to naming, resource handling, etc.
- A client is able to
  - Fetch a list of all anomalies, excluding observations.
  - Fetch information about a specific anomaly, including observations.
  - Add new anomalies
  - Add new observations for a specific anomaly
- A client is able to
  - Fetch information based on some search criteria, such as anomaly categories or a date range of observations.
   - Update information about anomalies and observations
   - Delete information about anomalies and observations


## Security Requirements

- Spring Security is used to secure the application.
- The fetch API endpoints should be accessible to anyone.
- The add/update/delete API endpoints should be available to authenticated users only.
- Bearer authentication with JSON Web Tokens (JWT)
  - If using JWT the application should provide an endpoint which returns a token. This endpoint should use HTTP Basic authentication. The secured endpoints should then expect and validate this token before granting access to the resources.
- New users are able to sign up to the web service programmatically using an endpoint.
- User accounts are stored in a database.


## Documentation Requirements

- The API has a basic OpenAPI specification, generated from the implementation.
- The API has a SwaHagger UI integration.


## Testing Requirements

- The application can be tested interactively with Swagger UI.
- The application has integration tests with @SpringBootTest
