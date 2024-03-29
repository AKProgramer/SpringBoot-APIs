Spring Boot APIs
This repository contains code for a Spring Boot project that provides APIs for user signup, user retrieval, and user deletion functionalities.

Modules
com.example.back_to_flutter.web.SignupController
This module defines a RESTful controller SignupController responsible for handling signup requests. It provides endpoints for user signup, retrieval, and deletion.

GET /: A test endpoint returning a simple "Hello" message.
POST /signup: Endpoint for user signup. Accepts signup credentials in the request body and returns the created user.
GET /getUser/{id}: Endpoint for retrieving a user by their ID.
GET /getAllUsers: Endpoint for retrieving all users.
DELETE /deleteAllUsers: Endpoint for deleting all users.
DELETE /deleteUser/{id}: Endpoint for deleting a user by their ID.
com.example.back_to_flutter.service.SignupServiceImp
This module contains the implementation of the SignupService interface, which defines methods for user signup, retrieval, and deletion.

com.example.back_to_flutter.security.SecurityConfig
This module configures security settings for the Spring Boot application. It defines security filters and authentication mechanisms using Spring Security.

com.example.back_to_flutter.security.SecurityConstants
This module contains constants related to security settings, such as secret keys and authorization headers.

com.example.back_to_flutter.security.filter.AuthenticationFilter
This module implements an authentication filter responsible for authenticating users during login/signup processes.

com.example.back_to_flutter.security.filter.JWTAuthorizationFilter
This module implements a JWT authorization filter responsible for verifying JWT tokens in API requests.

com.example.back_to_flutter.security.filter.ExaptionHandler
This module implements a filter to handle exceptions thrown during API requests. It catches exceptions like EntityNotFoundException and RuntimeException and returns appropriate HTTP responses.

Usage
To use this project:

Clone the repository to your local machine.
Import the project into your preferred Java IDE.
Configure any necessary database connections or environment variables.
Run the Spring Boot application.
Access the provided APIs using appropriate HTTP requests.
Feel free to explore and modify the code as needed for your application requirements. If you encounter any issues or have questions, please refer to the relevant module documentation or raise an issue on GitHub.
