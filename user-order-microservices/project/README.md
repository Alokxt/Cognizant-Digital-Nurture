# User & Order Management System (Microservices)

Two independent Spring Boot microservices:

- **user-service** (port `8081`) — owns user data, backed by its own PostgreSQL database (`userdb`).
- **order-service** (port `8082`) — owns order data, backed by its own PostgreSQL database (`orderdb`). It calls `user-service` over HTTP using **Spring WebFlux's `WebClient`** to validate users and enrich order responses with user details.

Each service owns its own database — this is the standard microservices pattern (database-per-service), so the two are fully independent and deployable separately.

## Tech stack
- Java 17, Spring Boot 3.3
- Spring Web (REST controllers)
- Spring WebFlux `WebClient` for service-to-service calls (order-service → user-service)
- Spring Data JPA + PostgreSQL
- Bean Validation (`jakarta.validation`)
- Lombok
- Maven

## Project layout
```
project/
├── user-service/       # User microservice
├── order-service/       # Order microservice
└── docker-compose.yml   # Spins up both DBs + both services
```

## Option A: Run everything with Docker Compose (easiest)

Requires Docker + Docker Compose installed.

```bash
cd project
docker compose up --build
```

This starts:
- `user-db` (Postgres, host port 5432)
- `order-db` (Postgres, host port 5433)
- `user-service` on http://localhost:8081
- `order-service` on http://localhost:8082 (configured to call `user-service` internally)

## Option B: Run locally without Docker

1. Install PostgreSQL locally and create two databases:
   ```sql
   CREATE DATABASE userdb;
   CREATE DATABASE orderdb;
   ```
2. Start user-service:
   ```bash
   cd user-service
   mvn spring-boot:run
   ```
   By default it connects to `jdbc:postgresql://localhost:5432/userdb` with user/pass `postgres/postgres`. Override with env vars `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USER`, `DB_PASSWORD` if needed.

3. In a second terminal, start order-service (needs a Postgres instance on a different port/DB, e.g. run a second local Postgres on 5433, or just point `DB_PORT`/`DB_NAME` at a second database on the same instance):
   ```bash
   cd order-service
   DB_PORT=5432 DB_NAME=orderdb mvn spring-boot:run
   ```
   It calls user-service at `http://localhost:8081` by default (override with `USER_SERVICE_URL`).

## API Reference

### User Service — `http://localhost:8081`

| Method | Endpoint             | Description         |
|--------|-----------------------|----------------------|
| POST   | `/api/users`          | Create a user        |
| GET    | `/api/users`          | List all users       |
| GET    | `/api/users/{id}`     | Get a user by id      |
| PUT    | `/api/users/{id}`     | Update a user         |
| DELETE | `/api/users/{id}`     | Delete a user         |

**Create user**
```bash
curl -X POST http://localhost:8081/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","email":"jane@example.com","phone":"9999999999"}'
```

### Order Service — `http://localhost:8082`

| Method | Endpoint                     | Description                                   |
|--------|-------------------------------|------------------------------------------------|
| POST   | `/api/orders`                 | Create an order (validates userId via user-service) |
| GET    | `/api/orders`                 | List all orders (each enriched with user info) |
| GET    | `/api/orders/{id}`            | Get an order by id (enriched with user info)    |
| GET    | `/api/orders/user/{userId}`   | Get all orders for a user                       |
| PATCH  | `/api/orders/{id}/status?status=SHIPPED` | Update order status                  |
| DELETE | `/api/orders/{id}`            | Delete an order                                 |

**Create order** (replace `1` with a real user id returned from user-service)
```bash
curl -X POST http://localhost:8082/api/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"productName":"Wireless Mouse","quantity":2,"totalAmount":39.98}'
```

If `userId` doesn't exist in user-service, order-service returns `400 Bad Request`.
If user-service is unreachable, order-service returns `503 Service Unavailable` on create (and degrades gracefully on reads — it still returns order data with `"user": null` rather than failing the whole request).

## How the inter-service call works

`order-service` has a `UserClient` (`client/UserClient.java`) that wraps a Spring WebFlux `WebClient` bean (`config/WebClientConfig.java`), pointed at `user-service`'s base URL via the `user-service.base-url` property (`USER_SERVICE_URL` env var in Docker).

- On **order creation**, it calls `GET /api/users/{id}` on user-service to confirm the user exists before saving the order.
- On **order reads**, it calls the same endpoint to enrich the response with the user's name/email/phone.
- It includes timeout + a small retry-with-backoff for transient failures, and maps 404 → `InvalidUserException` (400) and connection/5xx errors → `UserServiceUnavailableException` (503).

## Notes / possible extensions
- Swap `WebClient` for **OpenFeign** if you prefer a declarative client — the `UserClient` interface boundary is already isolated, so only `client/` and the Maven dependency would change.
- Add an API Gateway (Spring Cloud Gateway) and service discovery (Eureka/Consul) in front of both services for a more complete microservices setup.
- Add Flyway/Liquibase migrations instead of `ddl-auto: update` for production use.
