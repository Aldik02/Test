# Test task for Akvelon | Token Aldiyar
As a project, I chose the topic of reserving visits to sports complexes. This is an analogue of the 1 FIT mobile app

## Technologies
* Spring Boot
* Thymeleaf
* Hibernate
* Swagger
* Lombok
* Spring Security
* Bootify
* Maven
* JPA
* H2 In-Memory Database{the criteria indicated that you need to use Postgresql. But due to technical problems pgAdmin did not start, so I used the H2 database}
* Bootstrap

## Use Cases / User Stories

* Users should be able to log in.
* We will assume that the resident accounts have not been previously created and have made the registration function.
* Users should be able to view their reservations.
* Users should be able to create new reservations by selecting the amenity type, date, and time.
* Only logged-in users should be able to see the reservations page and create reservations.
* We should check the capacity and only create new reservations if the current number of reservations does not exceed the capacity.
