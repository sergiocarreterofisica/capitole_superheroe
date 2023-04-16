# Superheroes Rest Service

Esta API Rest sirve para gestionar el mantenimiento de un listado de superhéroes.

Utiliza la versión 2.7.10 de SpringBoot y la versión 11 de Java. Se utiliza una BBDD en memoria H2.

Actualmente utiliza las siguientes librerías añadidas al proyecto generado desde [Spring Initializr](https://start.spring.io/).
1. Lombok para evitar boiling code.
2. MapStruct para el mapeo entre objetos.
3. Commons-lang3 de Apache.
4. h2 para la BBDD en memoria.

Para las pruebas de integración se dispone de dos archivos relevantes:

a. /src/main/resources/data.sql

que sirve para que al arrancar la aplicación ya se disponga de datos en la BBDD en memoria.

b. src\main\resources\postman\SuperHero.postman_collection.json

que es una colección de Postman que cubre las pruebas de integración que se han realizado.

También se tiene acceso a SwaggerUi desde [Swagger-Ui](http://localhost:8080/swagger-ui/index.html).