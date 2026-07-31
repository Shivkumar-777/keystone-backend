# Keystone Backend

A Spring Boot REST API for managing companies, customers, technicians, work orders, inventory, service reports, notifications, authentication, and file uploads.

---

# Features

- JWT Authentication
- Role-Based Authorization
- Company Management
- Customer Management
- Site Management
- Technician Management
- Work Order Management
- Inventory Management
- Service Report Management
- Notification System
- File Upload Support
- SLA Monitoring Scheduler
- Pagination & Search
- Flyway Database Migration
- Swagger / OpenAPI Documentation
- Global Exception Handling
- PostgreSQL Database

---

# Technology Stack

## Backend

- Java 21
- Spring Boot 3.5.5
- Spring Security
- Spring Data JPA
- Hibernate
- Flyway
- PostgreSQL
- JWT
- Maven
- Lombok

## API Documentation

- Swagger OpenAPI

---

# Project Structure

```
src
├── main
│   ├── java
│   │   └── com.shivkumar.keystonebackend
│   │       ├── config
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── enums
│   │       ├── exception
│   │       ├── repository
│   │       ├── scheduler
│   │       ├── security
│   │       ├── service
│   │       └── KeystoneBackendApplication.java
│   │
│   └── resources
│       ├── application.properties
│       └── db
│           └── migration
│
└── test
```

---

# Modules

- Authentication
- Companies
- Customers
- Sites
- Technicians
- Work Orders
- Inventory Parts
- Service Reports
- Notifications
- File Uploads
- Scheduler
- Security
- Flyway Migration
- Swagger Documentation

---

# Prerequisites

Install the following software:

- Java 21
- Maven 3.9+
- PostgreSQL 17+
- Git
- IntelliJ IDEA

---

# Clone Project

```bash
git clone https://github.com/your-username/keystone-backend.git
cd keystone-backend
```

---

# Database Configuration

Create a PostgreSQL database.

Database Name

```
keystone
```

Update `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/keystone
spring.datasource.username=postgres
spring.datasource.password=your_password
```

---

# Run the Project

Using Maven

```bash
mvn spring-boot:run
```

Or build the project

```bash
mvn clean package
```

Run the generated JAR

```bash
java -jar target/keystone-backend-0.0.1-SNAPSHOT.jar
```

---

# Flyway

Database migration scripts are located in

```
src/main/resources/db/migration
```

Flyway automatically executes pending migrations when the application starts.

---

# API Documentation

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI JSON

```
http://localhost:8080/v3/api-docs
```

---

# Security

Public Endpoints

```
/api/auth/**
/swagger-ui/**
/v3/api-docs/**
/uploads/**
```

All other endpoints require a valid JWT token.

---

# Main REST APIs

## Authentication

```
POST /api/auth/register
POST /api/auth/login
```

## Company

```
GET    /api/companies
POST   /api/companies
PUT    /api/companies/{id}
DELETE /api/companies/{id}
```

## Customer

```
GET    /api/customers
POST   /api/customers
PUT    /api/customers/{id}
DELETE /api/customers/{id}
```

## Site

```
GET    /api/sites
POST   /api/sites
PUT    /api/sites/{id}
DELETE /api/sites/{id}
```

## Technician

```
GET    /api/technicians
POST   /api/technicians
PUT    /api/technicians/{id}
DELETE /api/technicians/{id}
```

## Work Order

```
GET    /api/work-orders
POST   /api/work-orders
PUT    /api/work-orders/{id}
DELETE /api/work-orders/{id}
```

## Inventory

```
GET    /api/inventory-parts
POST   /api/inventory-parts
PUT    /api/inventory-parts/{id}
DELETE /api/inventory-parts/{id}
```

## Service Reports

```
GET    /api/service-reports
POST   /api/service-reports
PUT    /api/service-reports/{id}
DELETE /api/service-reports/{id}
```

---

# Build

Clean project

```bash
mvn clean
```

Compile

```bash
mvn compile
```

Run Tests

```bash
mvn test
```

Package

```bash
mvn clean package
```

---

# Future Improvements

- Email Notifications
- Docker Support
- CI/CD Pipeline
- Redis Caching
- Audit Logging
- Monitoring with Spring Boot Actuator

---

# Author

**Shivkumar Vilas Kapse**

BE Electronics & Telecommunication Engineering

NBN Sinhgad Technical Institutes Campus, Pune

GitHub:
https://github.com/Shivkumar-777

---

# License

This project is developed for educational and portfolio purposes.