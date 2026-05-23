# 🐾 Centro Animal - Sistema de Microservicios

## Integrantes
- Daniela Belca

## Descripción
Sistema de gestión integral para un centro animal, desarrollado con arquitectura de microservicios en Spring Boot. 
Permite gestionar usuarios, animales, adopciones, donaciones, padrinos, voluntariados, visitas, matches, notificaciones y reportes del centro.

## Microservicios
| Microservicio | Puerto | Descripción |
|---|---|---|
| ms-usuario | 8080 | Registro y gestión de usuarios del sistema |
| ms-animales | 8081 | Gestión de animales disponibles en el centro |
| ms-adopciones | 8082 | Proceso de adopción de animales |
| ms-donaciones | 8083 | Registro de donaciones al centro |
| ms-padrinos | 8084 | Gestión de padrinos de animales |
| ms-voluntariados | 8085 | Postulaciones y gestión de voluntarios |
| ms-visitas | 8086 | Agendamiento de visitas al centro |
| ms-match | 8087 | Matching de usuarios con animales compatibles |
| ms-notificaciones | 8088 | Envío de notificaciones a usuarios |
| ms-reportes | 8089 | Generación de reportes del centro |

## Tecnologías utilizadas
- Java 21
- Spring Boot 3.5.14
- MySQL
- Laragon
- Spring Data JPA + Hibernate
- Flyway
- Lombok
- Bean Validation
- SLF4J
- OpenFeign

## Comunicación entre microservicios
- ms-adopciones consulta ms-animales para verificar existencia del animal
- ms-visitas consulta ms-animales para verificar existencia del animal
- ms-padrinos consulta ms-animales para verificar existencia del animal

## Pasos para ejecutar
1. Clonar el repositorio: `git clone https://github.com/Dabelca/Centroanimal-Fullstack.git`
2. Tener Laragon corriendo con MySQL activo
3. Abrir cada microservicio en IntelliJ IDEA
4. Ejecutar cada microservicio comenzando por ms-usuario
5. Probar los endpoints con Postman

## GitHub
https://github.com/Dabelca/Centroanimal-Fullstack
