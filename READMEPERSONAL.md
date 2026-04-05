# Sistema de Microservicios (Backend) — Java 21 + Spring Boot 3.x
**Arquitectura por servicio:** Hexagonal + Vertical Slicing + Screaming Architecture  
**Orquestación local:** Docker Compose (PostgreSQL por servicio)  
**Comunicación interna:** REST síncrono (Kafka/Outbox queda como fase opcional)  
**Entrada única:** API Gateway (Spring Cloud Gateway)  
**Seguridad:** JWT (fase posterior)  
**Nota:** No incluye frontend (solo backend).

## Objetivo (entrevistas)
- API Gateway como punto de entrada.
- 3 microservicios: catalog, orders, payments.
- DB por servicio + Flyway.
- REST interno con timeouts y manejo de errores.
- JWT en gateway y servicios (Corte 4).

## Contratos HTTP (vía Gateway)
- GET  /api/catalog/products
- GET  /api/catalog/products/{id}
- POST /api/orders
- GET  /api/orders/{id}
- POST /api/payments/authorize

## Cortes
### Corte 0 — Hola microservicios
- Actuator health en todos.
- Gateway enruta:
  - /api/catalog/** -> catalog-service
  - /api/orders/** -> orders-service
  - /api/payments/** -> payments-service
- Perfiles:
  - local: gateway -> localhost:8081/8082/8083
  - compose: gateway -> DNS de compose (catalog-service:8080, etc.)
- Docker Compose levanta Postgres por servicio.

### Corte 1 — Orders mínimo
- Create/Get order con Postgres + Flyway.
- Hexagonal + vertical slice: order/{domain,application,adapters}.

### Corte 2 — REST interno
- Orders valida producto en Catalog.
- Orders autoriza pago en Payments.
- Feign + timeouts + errores.

### Corte 4 — JWT
- Gateway + services como resource server.
- Health público, /api/** protegido.

# Notas de entrevistas y fundamentos — Microservicios + Java + Spring (Corte 0–1)

## 1) ¿Qué construimos hasta ahora?
Un backend con:
- **API Gateway** (`gateway`) como punto de entrada único.
- Microservicios:
  - `catalog-service`
  - `orders-service`
  - `payments-service`
- **PostgreSQL por servicio** (database-per-service) levantado con Docker Compose.
- **Actuator** para health-checks.
- En `orders-service`, un **Corte 1**: crear y consultar órdenes con persistencia real (Flyway + JPA).

### ¿Cómo fluye una petición (ejemplo)?
1) Cliente llama al gateway: `POST http://localhost:8080/api/orders`
2) Gateway enruta a `orders-service` (por regla `Path=/api/orders/**` + `StripPrefix=2`)
3) `orders-service` recibe `POST /` en su controller y ejecuta el caso de uso.
4) El caso de uso crea una orden en memoria y la guarda usando un **puerto** (`OrderRepositoryPort`).
5) El adapter de persistencia implementa el puerto usando **JPA** y Postgres.
6) Se responde con el `id` de la orden (UUID).

---

## 2) Microservicios (conceptos clave de entrevista)
### 2.1 ¿Qué es un microservicio?
Un microservicio es una aplicación pequeña que:
- Tiene un **dominio** acotado (bounded context).
- Se despliega de forma independiente.
- Tiene su **propia base de datos** (idealmente).
- Se comunica con otros servicios por red (HTTP/events).

### 2.2 Database per service (por qué importa)
Cada microservicio tiene su DB para:
- Evitar acoplamiento fuerte (no “shared DB”).
- Permitir desplegar/cambiar un servicio sin romper a otros.
- Controlar esquema y migraciones por servicio (Flyway).

Tradeoff: aparecen retos de consistencia entre servicios (se resuelve con eventos, sagas, etc. en etapas posteriores).

### 2.3 API Gateway (qué es y por qué lo usamos)
Un **API Gateway** es un componente en el borde que centraliza:
- **Routing** (enrutar `/api/orders/**` al servicio correcto).
- Seguridad (JWT, OAuth2), rate limiting, CORS, observabilidad (más adelante).
- Oculta la topología interna: el cliente no sabe en qué puerto vive cada servicio.

---

## 3) Arquitectura Hexagonal (estricta) + Vertical Slicing + Screaming
### 3.1 Hexagonal (Ports & Adapters)
El objetivo es que el core del negocio no dependa de frameworks.

- **Domain**: reglas y modelos (puro Java)
- **Application**: casos de uso + puertos (interfaces) (puro Java)
- **Adapters**: integración con el mundo (Spring REST, JPA, HTTP clients)
- **Configuration**: wiring (Spring) para conectar implementaciones

**Regla:** Domain/Application NO usan anotaciones Spring.

### 3.2 Puertos (Ports)
Un puerto es una interfaz que define una necesidad:
- Ejemplo: `OrderRepositoryPort` (guardar y leer órdenes)

El caso de uso depende del puerto, no de JPA ni de Postgres.

### 3.3 Adaptadores (Adapters)
Implementan puertos con una tecnología:
- `OrderPersistenceAdapter` implementa `OrderRepositoryPort` usando JPA.

### 3.4 Vertical slicing
Organización por feature:
- `order/` contiene domain + application + adapters relacionados con ordenes.
Evita paquetes genéricos como `controller/ service/ repository/` como estructura principal.

### 3.5 Screaming architecture
La estructura “grita” el negocio:
- `order/`, `product/`, `payment/`
No grita tecnología (`jpa/`, `web/`) en la raíz.

---

## 4) Java básico para entrevistas (lo que estás usando)
### 4.1 ¿Qué es una `class`?
Una `class` define un tipo con:
- Estado (atributos/campos)
- Comportamiento (métodos)

Ejemplo: `Order` es una clase del dominio.

### 4.2 ¿Qué significa `private`?
`private` restringe el acceso a un miembro (campo/método/constructor):
- Solo se puede acceder dentro de la misma clase.

En el dominio usamos constructor `private` para:
- Controlar la creación del objeto (factory methods)
- Asegurar invariantes

Ejemplo:
- `Order.createNew(...)` crea una orden nueva
- `Order.restore(...)` rehidrata una orden desde DB

### 4.3 ¿Qué es `final`?
- En variables: no se puede reasignar.
- En campos: el campo se asigna una vez (inmutabilidad).
- En clases: no puede ser extendida.

Usar objetos inmutables (mucho `final`) ayuda en:
- Concurrencia
- Razonamiento del código
- Evitar bugs por mutación

### 4.4 ¿Qué es `record` (Java)?
Un `record` es un tipo especial (Java 16+) para datos inmutables:
- Genera automáticamente: constructor, `equals`, `hashCode`, `toString`
- Ideal para DTOs (request/response) porque son “solo datos”.

Ejemplo:
```java
public record CreateOrderResponse(UUID id) {}
