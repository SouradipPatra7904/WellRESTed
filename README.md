## `README.md` (quick start)
```
# WellRESTed — initial scaffold


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


Next suggested step: security (cookie-based auth with Spring Security) or extend JPA (Vlad Mihalcea's keyset utilities / native queries & DTO projection). Which should we do next?
```


---


That's the first incremental scaffold. If you'd like, I can:
- generate the project files as downloadable zip, or
- add Security (cookie-based) next, or
- extend the JPA layer to include advanced keyset pagination with cursor tokens, or
- wire-in a sample integration test using Testcontainers + Postgres.