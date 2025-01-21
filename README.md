# JWT Authentication and CRUD API with Spring Boot

This project demonstrates JWT-based authentication and CRUD operations using Spring Boot and MySQL.

## Features

*   JWT Authentication (Signup, Login, Logout)
*   CRUD Operations (Create, Read, Update, Delete)
*   MySQL Database Integration
*   Spring Security Integration

## Prerequisites

*   Java 11+
*   Maven
*   MySQL Server

## Setup and Running

1.  **Clone the Repository:**

    ```bash
    git clone [invalid URL removed]  # Replace with your repo URL
    cd YOUR_REPO_NAME
    ```

2.  **Database Configuration (`src/main/resources/application.properties`):**

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC # Add these params to avoid timezone and SSL issues
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update # Use 'create' for initial setup, then 'update' or 'none' for production
    spring.security.jwt.secret=your-256-bit-secret-key-very-long-and-random # Generate a strong secret!
    spring.security.jwt.expiration=86400000 # Token expiration time in milliseconds (1 day)
    ```

    *   **Important:** Replace placeholders like `your_database_name`, `your_username`, `your_password`, and especially `your-256-bit-secret-key-very-long-and-random` with your actual values. The secret key *must* be strong and randomly generated.
    *   The additional parameters in the JDBC URL are important for avoiding common connection issues.
    *   The `spring.jpa.hibernate.ddl-auto` property controls database schema generation. Use `create` initially, then switch to `update` or `none` in production.

3.  **Build and Run:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## API Endpoints

**Base URL:** `http://localhost:8080`

**Authentication:**

*   **Signup (`POST /api/auth/signup`):**

    ```json
    {
        "username": "newuser",
        "password": "password123"
    }
    ```

    *   Response: 201 Created (Success), 400 Bad Request (User already exists)

*   **Login (`POST /api/auth/login`):**

    ```json
    {
        "username": "newuser",
        "password": "password123"
    }
    ```

    *   Response: 200 OK with JWT token:

        ```json
        {
            "token": "your_jwt_token"
        }
        ```

    *   401 Unauthorized (Invalid credentials)

*   **Logout (`POST /api/auth/logout`):**

    *   Headers: `Authorization: Bearer your_jwt_token`
    *   Response: 200 OK (Success)

**Resource Management (Protected Endpoints - Requires JWT):**

*   **Get All Resources (`GET /api/resources/all`):**

    *   Headers: `Authorization: Bearer your_jwt_token`
    *   Response: 200 OK with a list of resources, 403 Forbidden (Unauthorized)

*   **Create Resource (`POST /api/resources`):**

    *   Headers: `Authorization: Bearer your_jwt_token`
    *   Request Body:

        ```json
        {
            "name": "Resource Name",
            "description": "Resource Description"
        }
        ```

    *   Response: 201 Created (Success), 403 Forbidden

*   **Update Resource (`PUT /api/resources/{id}`):**

    *   Headers: `Authorization: Bearer your_jwt_token`
    *   Request Body:

        ```json
        {
            "name": "Updated Name",
            "description": "Updated Description"
        }
        ```

    *   Response: 200 OK (Success), 403 Forbidden, 404 Not Found

*   **Delete Resource (`DELETE /api/resources/{id}`):**

    *   Headers: `Authorization: Bearer your_jwt_token`
    *   Response: 200 OK (Success), 403 Forbidden, 404 Not Found

## Error Handling

*   401 Unauthorized: Invalid or missing JWT.
*   403 Forbidden: Insufficient permissions.
*   404 Not Found: Resource not found.
*   Other standard HTTP error codes.

## Testing

Use a REST client like Postman, Insomnia, or curl to test the API endpoints. Remember to include the `Authorization` header with the `Bearer` token for protected endpoints.

