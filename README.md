Smart Workflow Engine

A backend service built using Spring Boot to manage tasks with status tracking and priority handling.

Features

- Create, update, and delete tasks
- Track task status (TODO, IN_PROGRESS, DONE)
- Set task priority (LOW, MEDIUM, HIGH)
- Basic validation and error handling

Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Maven
- Lombok

Project Structure

controller → service → repository → entity

How to Run

mvn clean install
mvn spring-boot:run

Note

This project is built for learning backend development concepts like layering, validation, and API design.
