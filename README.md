
---

# Book Management Service

This project is a simple Spring Boot application that provides a REST API for managing a collection of books. The API allows you to add, retrieve, update, and delete books from a database using basic CRUD operations.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- **Java 11 or later** installed.
- **Maven** for building and managing dependencies.
- **IntelliJ IDEA** or another IDE to run the Spring Boot application.
- **Postman** or **curl** to test the REST API.

## How to Run the Application

1. **Clone the repository** or download the project files.
2. **Open the project** in IntelliJ:
    - Navigate to `File -> Open` and select the root folder of the project.
3. **Add Lombok Plugin** (if necessary):
    - Go to `File -> Settings -> Plugins` in IntelliJ and search for **Lombok**. Install the plugin and restart the IDE.
    - Enable annotation processing: `File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors -> Enable annotation processing`.
4. **Build the project**:
    - Right-click on the `BookmanagementserviceApplication.java` class and select **Run 'BookmanagementserviceApplication'**.
    - The application will start and run on `http://localhost:8080`.

## Database Setup

The project uses the **H2 in-memory database**, so no external database setup is required. The following configuration is used:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

To access the H2 console, go to `http://localhost:8080/h2-console` and use:
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: (leave blank or use `password` if set).

## API Endpoints

### Add a Book (POST)
- **URL**: `/api/books`
- **Method**: POST
- **Body** (JSON example):
  ```json
  {
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "publicationYear": 2008
  }
  ```
- **Response**: The newly created book will be returned with an automatically generated ID.

### Get All Books (GET)
- **URL**: `/api/books`
- **Method**: GET
- **Response**: A list of all books in the database.

### Get a Book by ID (GET)
- **URL**: `/api/books/{id}`
- **Method**: GET
- **Response**: The book corresponding to the given ID.

### Update a Book (PUT)
- **URL**: `/api/books/{id}`
- **Method**: PUT
- **Body** (JSON example):
  ```json
  {
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "publicationYear": 2017
  }
  ```
- **Response**: The updated book will be returned.

### Delete a Book (DELETE)
- **URL**: `/api/books/{id}`
- **Method**: DELETE
- **Response**: HTTP status `204 No Content`.

## Design Choices

1. **Layered Architecture**:
    - The project is designed using a standard layered architecture:
        - **Controller Layer**: Handles HTTP requests.
        - **Service Layer**: Contains business logic.
        - **Repository Layer**: Interacts with the database.
    - This separation ensures maintainability, readability, and the ability to scale different layers independently.

2. **Use of Lombok**:
    - **Lombok** is used to reduce boilerplate code by automatically generating getters, setters, and constructors, making the code cleaner and easier to maintain.

3. **H2 Database**:
    - The H2 in-memory database was chosen for simplicity and ease of testing. It allows the application to be quickly spun up without any external database setup, making it ideal for development and testing.

## Challenges Faced

1. **Version Conflicts with Lombok and JDK**:
    - Initially, there were issues related to Lombok version compatibility with the JDK. The problem was resolved by updating the Lombok dependency to a compatible version and ensuring that annotation processing was enabled in IntelliJ.

2. **Database Schema Auto-Update**:
    - There were some issues when configuring the automatic database schema generation (`spring.jpa.hibernate.ddl-auto=update`). This was resolved by ensuring the correct JPA and Hibernate configurations in `application.properties`.

3. **Handling Errors for Non-Existent Entities**:
    - When trying to update or delete a non-existent book, an appropriate error-handling mechanism was implemented using `Optional` in the service layer to handle null or missing values gracefully.

## Sample Requests and Responses

### Add Book
- **Request**:
  ```json
  {
    "title": "Refactoring",
    "author": "Martin Fowler",
    "publicationYear": 1999
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "title": "Refactoring",
    "author": "Martin Fowler",
    "publicationYear": 1999
  }
  ```

### Get All Books
- **Request**: `GET /api/books`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "title": "Refactoring",
      "author": "Martin Fowler",
      "publicationYear": 1999
    },
    {
      "id": 2,
      "title": "Clean Code",
      "author": "Robert C. Martin",
      "publicationYear": 2008
    }
  ]
  ```

### Update a Book
- **Request**:
  ```json
  {
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "publicationYear": 2018
  }
  ```
- **Response**:
  ```json
  {
    "id": 1,
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "publicationYear": 2018
  }
  ```

### Delete a Book
- **Request**: `DELETE /api/books/1`
- **Response**: HTTP Status `204 No Content`.
