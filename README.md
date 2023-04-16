# Superheroes Rest Service

Esta API Rest sirve para gestionar el mantenimiento de un listado de superhéroes.

Utiliza la versión 2.7.10 de SpringBoot y la versión 11 de Java. Se utiliza una BBDD en memoria H2.

Actualmente utiliza las siguientes librerías añadidas al proyecto generado desde https://start.spring.io/.
1. Lombok.
2. MapStruct para el mapeo entre objetos.

Para las pruebas de integración se dispone de dos archivos relevantes:

1. /src/main/resources/data.sql

  que sirve para que al arrancar la aplicación, ya se disponga de datos en la BBDD en memoria.

2. src\main\resources\postman\SuperHero.postman_collection.json

que es una colección de Postman que cubre las pruebas de integración que se han realizado.
