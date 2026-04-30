# RUWICH API

Spring Boot application using Swagger UI and PostgreSQL database.

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
- JJWT


## API Documentation

Swagger UI:</br>
http://localhost:8080/swagger-ui/index.html </br>

OpenAPI docummentation:</br>
http://localhost:8080/v3/api-docs</br></br>
