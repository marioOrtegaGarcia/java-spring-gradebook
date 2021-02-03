# Gradebook

This Gradebook API was created using the Java Spring Boot API.

## Gradebook RESET API Documentation
Run application and visit link  
[http://localhost:8080/swagger-ui/][Gradebook REST API Documentation]

## Features

### Controllers

- Returns Response Entity's to have full control of the HTTP Response
    - Status Codes, Headers, and Body
- Throws Exceptions when an issue occurs

### Services

- Handles the conversion Entity & Data Transfer Objects
- Serves helpful data:
  - Find missing Assignments for a given Student
  - Find Minimum, Average, and Maximum Grade for given Assignment
  - Calculates Letter Grade for Course
  - Find Courses without Professors


### Hibernate

- Uses JPQL Queries to fetch data from MySQL Database

### Aspect Oriented Programming

- Logging
  - Implements basic logging for application flow
    - Before and AfterReturning Method calls

## TODO

- [ ] Spring Security
  - [X] Create Security checks at Service layer @PreAuthorize
  - [X] Store User and Authorities in Database
  - [ ] Create a link between Users and Student/Teacher
- [ ] Improve Response Entity Status Codes
- [ ] Improve thrown Exceptions
  - [ ] Ensure proper Exceptions are thrown
  - [ ] Ensure Exceptions are handled properly
  
- [ ] Find more edge cases

[Gradebook REST API Documentation]: http://localhost:8080/swagger-ui/