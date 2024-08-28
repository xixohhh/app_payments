# app_payments

La aplicación Payments se basa en:

Dos Docker Images

**POSTGRESQL 16**   Base de datos (NO PERSISTENTE) se genera cuando creas el contenedor

 ```docker pull bitnami/postgresql:16 ```

**JDK17**  Contenedor con instalación mínima para la ejecución de aplicaciones java version17, en el cual ejecutaremos el microservicio Java.
 ```docker pull openjdk:17-jdk-alpine ```

Un microservicio desarrollado con Spring-boot que utiliza las dependencias:

spring-boot-starter-data-jpa

spring-boot-starter-web

org.projectlombok.lombok

org.springdoc.springdoc-openapi-starter-webmvc-ui

org.postgresql.postgresql

La aplicación Spring-boot levantara un servidor tomcat donde se ejecutara el microservicio Payments que consta de un Api con los siguientes endpoints

Se establece /api/v1.0/ como la raíz del Api

POST: "/api/v1.0/users/{userId}/payment" - Añadir un pago con tarjeta de credito

GET: "/api/v1.0/users/{userId}/payment" - Obtener un listado de pagos con tarjeta de crédito

Se utiliza la herramienta/plugin JACOCO integrado con Maven, que se encarga de realizar informes de análisis sobre cobertura del código mediante los Junit Test realizados. El umbral configurado es 80%.



- **REQUERIMIENTOS PARA EJECUTAR EN LOCAL**

1.- Tener instalado y configurado java17.

2.- Tener instalado Maven > 3.8 configurado para que utilice java17.

3.- Tener Docker y Docker compose instalado para ejecutar el archivo compose.

4.- Para abrir en IDE importar como proyecto Maven, (Creado en Eclipse)

5.- Tener un contenedor PostgreSQL 16, se facilitara el comando de creación y los parámetros de conexión.



- **PASOS PARA EJECUTAR LA APLICACION EN DOCKER**

1.- Descargar el proyecto con el comando ```git clone https://github.com/xixohhh/app_payments.git``` en la ruta donde queremos alojar el repositorio en local. La rama de debemos descargar es develop.

2.- Importar el proyecto en eclipse o el IDE que uticemos mediante la herramienta de importacion seleccionando un proyecto Maven existente.

3.- Comprobar que el archivo application.properties la propiedad spring.datasource.url es:
	```spring.datasource.url=jdbc:postgresql://database:5432/postgres?currentSchema=payments-db ```


4.- Desde consola navegar al repositorio git donde descargamos el proyecto y ejecutar el comando

  ```mvn clean install -Dmaven.test.skip=true ```

5.- Desde consola, en el repositorio git donde descargamos el proyecto, ejecutamos el comando " docker compose up -d " y esperamos que cargue los contenedores (Si es la primera vez tarda un poco).

OPCIONAL SI OCURRE ERROR: Crear una red de docker para comunicar los contenedores
 ```network create app-nttdata-network --driver bridge ```

- **PASOS PARA EJECUTAR LA APLICACION EN LOCAL/ECPLIPSE**

Los parámetros de conexión a la base de datos son:

ip:localhost
port:5432
username:postgres
password:1234

1.- Descargar el proyecto con el comando ```git clone https://github.com/xixohhh/app_payments.git``` en la ruta donde queremos alojar el repositorio en local. La rama de debemos descargar es develop.

2.- Importar el proyecto en eclipse mediante la herramienta de importacion seleccionando un proyecto Maven existente.

3.- Comprobar que el archivo application.properties la propiedad spring.datasource.url es:

 	```spring.datasource.url=jdbc:postgresql://localhost:5432/postgres?currentSchema=payments-db ```
  
4.- Levantar una base de datos PostgreSQL con el comando siguiente:

4.1.-- Crear una red de docker para comunicar el contenedor con localhost

	```network create app-nttdata-network --driver bridge ```
 
4.2-- Levantar el contenedor

	 ```docker run --name postgresql-server --network app-nttdata-network -p 5432:5432 --env=POSTGRESQL_PASSWORD=1234 bitnami/postgresql:16  ```

5.- Ejecutamos el script que se encuentra en la raíz del proyecto/db/database.sql

6.- Ejecutamos el fichero /payments/src/main/java/com/prueba/ntt/payments/PaymentsAppApplication.java que es la clase de inicio de la aplicación spring boot.



- **PROBAR INFORME JOCOCO**

Para que al construir la aplicación con Maven se genere el informe de cobertura de test, debemos lanzar el siguiente comando Maven:
 ```mvn clean install verify ```

En la ruta target/site/jacoco/ se generara dicha información en HTML, que podemos consultar ejecutando index.hmtl.


- **PROBAR APLICACION**

Tanto en LOCAL como en DOCKER se han mapeado los puertos tanto de la aplicacion como de la base de datos para que sean accesibles desde localhost.

Para probar la aplicacion nos ayudamos de un cliente HTTP como por ejemplo Postman.

POST: "localhost:8080/api/v1.0/users/1/payment" - Añadir un pago con tarjeta de credito

Payload/Body:

 ```
{
"ammount":18.51,
"creaditCard":"232313131311",
"description":"asdada",
"paymentDate":"2024-05-12T02:00:00"
}
```

GET: "localhost:8080/api/v1.0/users/1/payment" - Obtener un listado de pagos con tarjeta de crédito

Para probar swager acceder a:

 ```http://localhost:8080/api/v1.0/swagger-ui/index.html ```
