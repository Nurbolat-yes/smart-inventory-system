# Smart Inventory System

A REST API backend for managing warehouse inventory, built with Spring Boot. Features JWT authentication, product and stock tracking, low-stock alerting, audit logging, and geospatial warehouse lookup via Uber H3 indexing.

---

## Tech Stack

- **Java 21** + **Spring Boot 4**
- **Spring Security** with JWT
- **Spring Data JPA** + **PostgreSQL**
- **Uber H3** for geospatial indexing
- **Lombok**
- **Gradle**

---

## Prerequisites

- Java 21+
- PostgreSQL 14+
- Gradle (or use the included `./gradlew` wrapper)

---

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/smart-inventory-system.git
cd smart-inventory-system
```

### 2. Create the database

```sql
CREATE DATABASE inventory_db;
```

### 3. Configure environment

Copy the example env file and fill in your values:

```bash
cp .env.example .env
```

```env
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_password
POSTGRES_DB=inventory_db

JWT_SECRET=your_long_random_secret_here
```

Update `src/main/resources/application.properties` to use env vars:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/${POSTGRES_DB}
spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
app.jwt.secret=${JWT_SECRET}
app.jwt.expiration=86400000
```

### 4. Run the application

```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8080`.

---

## API Overview

### Authentication — `/api/auth`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and receive a JWT token |

All other endpoints require a `Bearer` token in the `Authorization` header.

### Products — `/api/products`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products` | List all products |
| POST | `/api/products` | Create a product |
| GET | `/api/products/{id}` | Get product by ID |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |
| GET | `/api/products/barcode/{barcode}` | Look up product by barcode |

### Stock — `/api/stock`

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/stock/update` | Update stock quantity |
| POST | `/api/stock/barcode` | Update stock by barcode scan |
| GET | `/api/stock/low-stock` | List low-stock items |
| GET | `/api/stock/warehouse/{warehouseId}` | Stock by warehouse |
| GET | `/api/stock/h3/{h3Index}` | Stock by H3 geospatial index |

### Warehouses — `/api/warehouses`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/warehouses` | List all warehouses |
| POST | `/api/warehouses` | Create a warehouse |
| GET | `/api/warehouses/{id}` | Get warehouse by ID |
| GET | `/api/warehouses/h3/{h3Index}` | Find warehouses by H3 index |

### Alerts — `/api/alerts`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/alerts` | List all alerts |
| GET | `/api/alerts/unresolved` | List unresolved alerts |
| PUT | `/api/alerts/{alertId}/resolve` | Mark alert as resolved |

### Analytics — `/api/analytics`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/analytics` | Get analytics data |
| POST | `/api/analytics/aggregate` | Trigger analytics aggregation |

---

## Roles

| Role | Description |
|------|-------------|
| `ADMIN` | Full access |
| `STAFF` | Can manage stock and products |
| `VIEWER` | Read-only access |

---

## License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
