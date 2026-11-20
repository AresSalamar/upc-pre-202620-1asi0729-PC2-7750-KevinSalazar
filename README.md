# Andes Logistics — solución de práctica

## Overview

Proyecto educativo Java 25 / Spring Boot 4.0.0 / MySQL con DDD, capas, CQRS para POST, Value Objects, Result, Resource/Assembler, i18n y Swagger. **Uso exclusivo como estudio previo.** El enunciado de PC2 prohíbe la generación de soluciones por IA y solo autoriza reutilizar los componentes específicos de `shared` desde `learning-center-platform` durante la evaluación; NO presentes este proyecto como trabajo propio del examen.


> **Repositorio GitHub:** `upc-pre-202620-1asi0729-PC2-7750-KevinSalazar`. El nombre del repositorio no define el paquete Java; conserva `u202417747` en los `package` e `import`.

## Authorship

**Repository:** https://github.com/AresSalamar/upc-pre-202620-1asi0729-PC2-7750-KevinSalazar

**Author:** Kevin Salazar  
**Student code:** U202417747

**Maven artifactId:** `pc27750u202417747`

## Requisitos

- JDK 25 y Maven 3.9+ (Spring Boot 4.0.0)
- MySQL 8+, schema `andes_logistics` (se crea con la opción del JDBC URL si el usuario dispone de permisos)
- Variables `DB_USER` y `DB_PASSWORD` configuradas en tu IDE o terminal. Nunca incluyas contraseñas en git.

## Cómo ejecutar

```bash
# En Windows PowerShell:
$env:DB_USER="root"
$env:DB_PASSWORD="TU_PASSWORD"
mvn spring-boot:run
```

Prueba `http://localhost:8080/swagger-ui/index.html` y `POST http://localhost:8080/api/v1/delivery-orders`. Para internacionalización usa `Accept-Language: es-PE` o `en-US`.

## JSON de ejemplo

```json
{
  "deliveryCode": "DEL-001",
  "warehouseId": "550e8400-e29b-41d4-a716-446655440000",
  "deliveryWindowStartDate": "2026-11-21",
  "deliveryWindowEndDate": "2026-11-25",
  "packageLoadQuantity": 10,
  "packageLoadUnit": "BOX",
  "priorityLevel": 3,
  "status": "PENDING",
  "deliveryNotes": "Example notes"
}
```

HTTP 201: objeto con `id`, campos primitivos y `status`. HTTP 400: datos inválidos. HTTP 409: código repetido. HTTP 500: falla inesperada. Ejemplo JSON de error `{"message":"Invalid request"}`.

## Estructura

- `logistics/domain`: agregado sin JPA; value objects; comando CQRS; contrato de repositorio
- `logistics/application`: servicio de comando que devuelve `Result`
- `logistics/infrastructure/persistence`: entidad JPA, Spring Data JPA y adapter
- `logistics/interfaces/rest`: request, controller, assembler, resource
- `shared`: Result y errores localizados

## Notas de adaptación

El paquete está personalizado con `u202417747`. En el examen respeta el nombre del paquete con tu código, el NRC, la base de datos, los mensajes y el nombre final del ZIP. La solución del repositorio es un ejemplo independiente para aprendizaje; no se garantiza compatibilidad con el ambiente del aula sin ejecutar Maven/MySQL.

Consulta `GIT-GITFLOW.md` para autenticación, remotos, commits por bounded context y release.
