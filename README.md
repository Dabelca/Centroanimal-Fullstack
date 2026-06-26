# 🐾 CentroAnimal - Sistema de Gestión para Centro de Adopción de Animales

**Estudiante:** Daniela Beltrán  
**Asignatura:** Desarrollo FullStack 1 (DSY1103)

---

## 📋 ¿Qué es CentroAnimal?

CentroAnimal es un sistema de gestión para un centro de adopción de animales, desarrollado con arquitectura de **microservicios**. Esto significa que en vez de tener un solo programa gigante, el sistema está dividido en 10 servicios independientes, cada uno responsable de una función específica (animales, adopciones, donaciones, etc.).

---

## 🧩 Microservicios del Sistema

| Microservicio | Descripción | Puerto |
|---|---|---|
| ms-usuario | Gestión de usuarios registrados | 9090 |
| ms-animales | Registro y estado de animales | 9091 |
| ms-adopciones | Proceso de adopción de animales | 9092 |
| ms-donaciones | Registro de donaciones al centro | 9093 |
| ms-padrinos | Apadrinamiento de animales | 9094 |
| ms-voluntariados | Postulaciones de voluntarios | 9095 |
| ms-visitas | Agendamiento de visitas al centro | 9096 |
| ms-match | Matching entre usuarios y animales | 9097 |
| ms-notificaciones | Envío de notificaciones a usuarios | 9098 |
| ms-reportes | Generación de reportes del sistema | 9099 |
| ms-gateway | Puerta de entrada única al sistema | 9010 |

---

## 🛠️ Tecnologías Utilizadas

- **Java 21** — Lenguaje de programación
- **Spring Boot 3.5** — Framework principal
- **Spring Cloud Gateway** — API Gateway
- **MySQL** — Base de datos (una por microservicio)
- **Flyway** — Migraciones automáticas de base de datos
- **OpenFeign** — Comunicación entre microservicios
- **Swagger/OpenAPI** — Documentación de APIs
- **JUnit 5 + Mockito** — Pruebas unitarias
- **Docker + Docker Compose** — Despliegue en contenedores
- **Lombok** — Reducción de código repetitivo

---

## 🚀 ¿Cómo ejecutar el proyecto?

### Requisitos previos
- Tener instalado **Docker Desktop**
- Tener instalado **Git**

### Pasos para ejecutar

**1. Clonar el repositorio**
```bash
git clone https://github.com/Dabelca/Centroanimal-Fullstack.git
cd Centroanimal-Fullstack
```

**2. Levantar todos los servicios con Docker**
```bash
docker-compose up
```

Eso es todo. Docker se encarga de:
- Crear la base de datos MySQL con las 10 bases de datos necesarias
- Construir y levantar los 10 microservicios
- Levantar el API Gateway

**3. Verificar que todo esté corriendo**

Abrir Docker Desktop y verificar que todos los contenedores estén en verde.

**4. Para detener el sistema**
```bash
docker-compose down
```

---

## 🌐 Rutas del API Gateway

Todas las peticiones pasan por el Gateway en el puerto **9010**. No es necesario conocer los puertos individuales de cada microservicio.

| Microservicio | Ruta Gateway |
|---|---|
| Usuarios | `http://localhost:9010/api/v1/usuarios` |
| Animales | `http://localhost:9010/api/v1/animales` |
| Adopciones | `http://localhost:9010/api/v1/adopciones` |
| Donaciones | `http://localhost:9010/api/v1/donaciones` |
| Padrinos | `http://localhost:9010/api/v1/padrinos` |
| Voluntariados | `http://localhost:9010/api/v1/voluntariados` |
| Visitas | `http://localhost:9010/api/visitas` |
| Matchings | `http://localhost:9010/api/matchings` |
| Notificaciones | `http://localhost:9010/api/notificaciones` |
| Reportes | `http://localhost:9010/api/v1/reportes` |

---

## 📖 Documentación Swagger

Cada microservicio tiene su propia documentación interactiva. Para acceder, levantar los servicios individualmente con IntelliJ y visitar:

| Microservicio | URL Swagger |
|---|---|
| ms-usuario | http://localhost:9090/doc/swagger-ui/index.html |
| ms-animales | http://localhost:9091/doc/swagger-ui/index.html |
| ms-adopciones | http://localhost:9092/doc/swagger-ui/index.html |
| ms-donaciones | http://localhost:9093/doc/swagger-ui/index.html |
| ms-padrinos | http://localhost:9094/doc/swagger-ui/index.html |
| ms-voluntariados | http://localhost:9095/doc/swagger-ui/index.html |
| ms-visitas | http://localhost:9096/doc/swagger-ui/index.html |
| ms-match | http://localhost:9097/doc/swagger-ui/index.html |
| ms-notificaciones | http://localhost:9098/doc/swagger-ui/index.html |
| ms-reportes | http://localhost:9099/doc/swagger-ui/index.html |

---

## 🧪 Pruebas Unitarias

Cada microservicio cuenta con pruebas unitarias desarrolladas con **JUnit 5** y **Mockito**, cubriendo los métodos principales del service y las reglas de negocio del sistema.

Para ejecutar las pruebas de un microservicio en IntelliJ:
1. Abrir la clase de test (ej: `AnimalServiceTest.java`)
2. Click derecho → **Run with Coverage**
3. Ver el reporte de cobertura generado

---

## 📁 Estructura del Proyecto

```
Centro-animal/
├── ms-animales/
├── ms-adopciones/
├── ms-donaciones/
├── ms-padrinos/
├── ms-voluntariados/
├── ms-visitas/
├── ms-match/
├── ms-notificaciones/
├── ms-reportes/
├── ms-usuario/
├── ms-gateway/
├── mysql-init/
│   └── init.sql
└── docker-compose.yml
```

---

## 📌 Notas

- El sistema usa una **base de datos separada por microservicio** para garantizar independencia entre servicios.
- La comunicación entre microservicios que necesitan datos de animales se realiza mediante **OpenFeign**.
- El **API Gateway** centraliza el acceso al sistema — no es necesario conocer los puertos individuales de cada MS.
