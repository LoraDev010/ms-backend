# ms-backend — Microservices Backend (Java 21 + Spring Boot 3.x)

Backend compuesto por un **API Gateway** y 3 microservicios (Catalog, Orders, Payments).  
El Gateway es el **único punto de entrada** para clientes y enruta las peticiones hacia cada servicio.

## Stack
- Java 21
- Spring Boot 3.x
- Spring Cloud Gateway (WebFlux)
- PostgreSQL (una base por servicio)
- Flyway (migraciones por servicio)
- Maven Wrapper (`mvnw.cmd`)

## Servicios
| Servicio | Rol | Puerto local (HTTP) |
|---|---|---:|
| `gateway` | Entrada única / routing | `8080` |
| `catalog-service` | Catálogo | `8081` |
| `orders-service` | Órdenes | `8082` |
| `payments-service` | Pagos | `8083` |

## Cómo se comunican (Gateway → Servicios)
En perfil `local`, el Gateway enruta así:
- `/api/catalog/**` → `http://localhost:8081`
- `/api/orders/**` → `http://localhost:8082`
- `/api/payments/**` → `http://localhost:8083`

El Gateway aplica `StripPrefix=2`, por lo que:
- `GET /api/orders/actuator/health` llega a `orders-service` como `GET /actuator/health`
- `POST /api/orders` llega a `orders-service` como `POST /`
- `GET /api/orders/{uuid}` llega a `orders-service` como `GET /{uuid}`

## Requisitos
- JDK 21 instalado (`java -version`)
- Docker Desktop corriendo
- Puertos libres: `8080–8083`, `5433–5435`

## Base de datos (Docker Compose)
Cada servicio usa su propia base de datos PostgreSQL.

Puertos DB (host):
- Catalog DB: `5433`
- Orders DB: `5434`
- Payments DB: `5435`

### Levantar DBs
Desde la raíz del repo:

```powershell
docker compose -f platform/compose/docker-compose.yml up -d
docker compose -f platform/compose/docker-compose.yml ps

### Ejecutar servicios locales
Abrir 4 terminales diferentes y ejecutar dentro de cada servicio el siguiente comando
- mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local

### Validar que todo este arriba (health checks)
Realizar GET desde postman a los diferentes servicios

- http://localhost:8080/api/catalog/actuator/health
- http://localhost:8080/api/orders/actuator/health
- http://localhost:8080/api/payments/actuator/health

respuesta esperada:
{ "status": "UP" }

### Probar servicio de order
Ejecutar desde postman la creacion de una orden

- POST http://localhost:8080/api/orders
Body: vacio
Respuesta esperada: 201 Created
  { "id": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx" }

Consultar Orden creada

- GET http://localhost:8080/api/orders/{uuid}
Respuesta esperada: 200 OK
  {
  "id": "xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx",
  "status": "CREATED",
  "createdAt": "2026-04-05T00:00:00Z"
  }

Nota: {uuid} debe ser un UUID válido; si envías 1 obtendrás 400 Bad Request.

