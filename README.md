JWT Authentication and CRUD API
This project is a Spring Boot application implementing JWT-based authentication with signup, login, logout, and CRUD operations on a MySQL database.

Features
JWT Authentication:
Signup
Login
Logout
CRUD Operations:
Create, Read, Update, Delete operations on resources
MySQL Database
Spring Security Integration
Prerequisites
Java 11 or later
Maven
MySQL Server
Project Setup
Clone the repository:
bash
Copy
Edit
git clone <repository-url>
cd <project-folder>
Configure the database in src/main/resources/application.properties:
properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.security.jwt.secret=your-256-bit-secret
spring.security.jwt.expiration=86400000
Build and run the project:
bash
Copy
Edit
mvn clean install
mvn spring-boot:run
API Endpoints
Authentication Endpoints
1. Signup
URL: /api/auth/signup
Method: POST
Request Body:
json
Copy
Edit
{
  "username": "testuser",
  "password": "password123"
}
Response:
201 Created if the user is successfully registered
400 Bad Request if the user already exists
2. Login
URL: /api/auth/login
Method: POST
Request Body:
json
Copy
Edit
{
  "username": "testuser",
  "password": "password123"
}
Response:
200 OK with a JWT token:
json
Copy
Edit
{
  "token": "your-jwt-token"
}
401 Unauthorized if credentials are invalid
3. Logout
URL: /api/auth/logout
Method: POST
Headers:
Authorization: Bearer your-jwt-token
Response:
200 OK if the token is successfully invalidated
CRUD Endpoints
1. Get All Resources
URL: /api/resources/all
Method: GET
Headers:
Authorization: Bearer your-jwt-token
Response:
200 OK with a list of resources
403 Forbidden if the user is not authenticated
2. Create a Resource
URL: /api/resources
Method: POST
Headers:
Authorization: Bearer your-jwt-token
Request Body:
json
Copy
Edit
{
  "name": "Resource Name",
  "description": "Resource Description"
}
Response:
201 Created if the resource is successfully created
403 Forbidden if the user is not authenticated
3. Update a Resource
URL: /api/resources/{id}
Method: PUT
Headers:
Authorization: Bearer your-jwt-token
Request Body:
json
Copy
Edit
{
  "name": "Updated Name",
  "description": "Updated Description"
}
Response:
200 OK if the resource is successfully updated
404 Not Found if the resource does not exist
4. Delete a Resource
URL: /api/resources/{id}
Method: DELETE
Headers:
Authorization: Bearer your-jwt-token
Response:
200 OK if the resource is successfully deleted
404 Not Found if the resource does not exist
Error Responses
401 Unauthorized: Missing or invalid JWT token
403 Forbidden: User lacks necessary permissions
404 Not Found: Resource not found
Testing the API
Use Postman or any REST client to test the endpoints.

Signup to create a new user.
Login to get a JWT token.
Use the token in the Authorization header to access protected endpoints.
Example Authorization Header
text
Copy
Edit
Authorization: Bearer your-jwt-token
License
This project is licensed under the MIT License.
