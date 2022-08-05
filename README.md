# backend-users-test
API Users

La propuesta de solución fue el desarrollo de un API utilizando las tecnologías Java Spring Boot, Spring Data, Spring Security, JWT, maven, Java 8.

Utilice la consola H2 para ver la base de datos, el usuario y clave es sa
http://localhost:8080/h2-console

Los API de desarrollados son los siguientes:

Endpoint 1 Create user
URI: http://localhost:8080/users
Tipo: POST
Respuesta: JSON representación del objeto UserInfoResponseDTO.
Caso Éxito:
Status: 201 Usuario creado con éxito.
Caso error:
Status: 400 en caso de error de validación
Status: 500 en caso de otro tipo de excepción.


Endpoint 2 Get all users
URI: http://localhost:8080/users
Tipo: GET

Para correr este proyecto ejecute:

1. mvn package

2. Ir al directorio \backend-users-test\target y ejecutar;

java -jar backend-users-test-0.0.1-SNAPSHOT.jar
