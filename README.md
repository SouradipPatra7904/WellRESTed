<<<<<<< HEAD
# WellRESTed

**Enterprise-Grade REST + SOAP Application with JPA**

---

## 📌 Project Overview
WellRESTed is a Spring Boot 3.5.6 application built with **Java 21** that goes far beyond CRUD. It demonstrates how REST and SOAP can seamlessly co-exist in a modern backend service, backed by **JPA + PostgreSQL**. The project emphasizes clean architecture, separation of concerns, and enterprise patterns.

**Artifact:** `WellRESTed`  
**Group:** `souradippatra`  
**Packaging:** `jar`  
**Build Tool:** `Maven`

---

## ✨ Features
- **RESTful Endpoints:** Full CRUD with GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD.
- **JPA with Pagination & DTO mapping:** Efficient entity handling, pagination, and response shaping.
- **HATEOAS Links:** Hypermedia-driven responses for richer REST APIs.
- **External REST Integration:** Fetches sleep recommendations from a mock REST API.
- **SOAP Integration:**
  - Exposes a SOAP endpoint (`/ws/sleepsession.wsdl`) with request/response schema.
  - Provides a SOAP client using `WebServiceTemplate`.
  - REST → SOAP bridge endpoint: `GET /api/sleep-sessions/{id}/soap`.
- **Caching:** In-memory caching for repeated recommendation lookups.
- **Actuator Config:** Health checks and basic monitoring.

---

## 🏗️ Architecture
- **Entity Layer:** JPA entities mapped to PostgreSQL.
- **DTO Layer:** Clean separation between persistence and API payloads.
- **Service Layer:** Business logic with interfaces + implementations.
- **Controller Layer:** REST endpoints + error handling via `@ControllerAdvice`.
- **SOAP Layer:** `@Endpoint` annotated handlers for SOAP requests.
- **Config Layer:** Separate configuration for Actuator, HATEOAS, and SOAP services.

---

## 🔧 Tech Stack
- **Spring Boot 3.5.6**
- **Java 21**
- **Spring Data JPA + PostgreSQL**
- **Spring Web + HATEOAS**
- **Spring-WS (SOAP)**
- **Spring Cache (in-memory)**
- **Spring Boot Actuator**
- **Lombok**

---

## 🚀 Running the Project
1. Clone the repository.

2. Configure PostgreSQL in `application.yml`.

3. Run with Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Access endpoints:
   - REST API → `http://localhost:9595/api/sleep-sessions`
   - SOAP WSDL → `http://localhost:9595/ws/sleepsession.wsdl`
   - REST → SOAP bridge → `http://localhost:9595/api/sleep-sessions/{id}/soap`

5. REST API specific endpoints :
    - GET /api/sleep-sessions
    - GET /api/sleep-sessions/{id}
    - POST /api/sleep-sessions
    - PATCH /api/sleep-sessions/{id}
    - PUT /api/sleep-sessions/{id}
    - DELETE /api/sleep-sessions/{id}
    - HEAD /api/sleep-sessions/{id}
    - OPTIONS /api/sleep-sessions

---

## 📈 Future Enhancements
1. Security with Spring Security.
2. Advanced JPA optimizations (Vlad Mihalcea patterns).
3. Database migrations (Flyway/Liquibase).
4. Docker support.
5. Testcontainers & Profiling.
6. Observability: Micrometer, Prometheus, Grafana.

---

## 🏆 Why my Project is so much more than basic "CRUD" apps :
Unlike typical CRUD demos, **WellRESTed** demonstrates:
- Advanced JPA usage.
- Integration with both **REST** and **SOAP**.
- Enterprise-grade separation of concerns.
- Extensible architecture for monitoring, security, and scalability.

=======

## WellRESTed
> An enterprise grade REST API with HATEOAS support and cookie based authentication.
```
1. Configure Postgres on localhost (or change application.yml).
2. mvn spring-boot:run
3. Sample endpoints:
- GET /api/sleep-sessions
- GET /api/sleep-sessions/{id}
- POST /api/sleep-sessions
- PATCH /api/sleep-sessions/{id}
- PUT /api/sleep-sessions/{id}
- DELETE /api/sleep-sessions/{id}
- HEAD /api/sleep-sessions/{id}
- OPTIONS /api/sleep-sessions
```
---
>>>>>>> 695bf78a1d78f94a17a43ffd2349010a6085f868
