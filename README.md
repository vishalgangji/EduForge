# EduForge: Student and Course Management System

## Overview

EduForge is a backend application designed to manage courses and student information. It features user authentication with JWT (JSON Web Token), ensuring data consistency, and provides functionality for student registration, modification, and deletion.

## Table of Contents

1. [Technologies Used](#technologies-used)
2. [Project Structure](#project-structure)
3. [Database Schema](#database-schema)
4. [Features](#features)
5. [How to Run](#how-to-run)
6. [Usage](#usage)

## Technologies Used

- Java Spring Boot
- Spring Data JPA
- Database (e.g., MySQL, H2)
- Spring Security
- Gradle/Maven (for dependency management)
- Any additional technologies/frameworks (if used)

## Project Structure

- `src/main/java/com/example/eduforge/`: Java source code
- `src/main/resources/`: Configuration files
- `src/test/`: Unit tests (if available)

## Database Schema

The project uses a relational database with two main tables:

- `Course` with fields: `course_id`, `course_name`, `course_code`, `course_duration`, etc.
- `Student` with fields: `student_id`, `student_name`, `student_email`, `address`, `phone_number`, `course_id` (foreign key).

## Features

1. **Course Management**
    - Create, read, update, and delete courses.
    - Ensure data integrity with transaction management during course operations.

2. **Student Management**
    - Register students with associated courses.
    - View student details with course information.
    - Update and delete student records with associated course modifications.

3. **User Authentication with JWT**
    - Implement JWT-based authentication for users.
    - Generate JWT tokens for authenticated users.
    - Secure API endpoints using JWT tokens.

## How to Run

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/eduforge.git
    cd eduforge
    ```

2. Configure the database in `src/main/resources/application.properties`.

3. Build and run the application:

    ```bash
    ./gradlew bootRun    # For Gradle
    # OR
    mvn spring-boot:run  # For Maven
    ```

4. Access the application at [http://localhost:8096](http://localhost:8096).

## Usage

1. **User Authentication with JWT**
    - Use `/authenticate` API endpoint to obtain a JWT token by providing valid credentials.
    - Include the obtained JWT token in the Authorization header for secure API access.

2. **Course Management**
    - Use appropriate API endpoints or UI for course management.

3. **Student Management**
    - Register, update, and delete students using provided APIs.



