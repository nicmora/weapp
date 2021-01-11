# Weapp - Wenance Challenge

La función principal de esta aplicación es conocer el precio del Bitcoin (BTC) con respecto al dolar (USD) en tiempo real, y realizar cálculos para obtener el valor promedio y variación de la criptomoneda en cierto rango de tiempo.

Para lograr esto se debe consumir el servicio brindado por la API:

[https://cex.io/api/last_price/BTC/USD](https://cex.io/api/last_price/BTC/USD)

## Requerimientos

- JDK 1.8.x.
- Maven 3.x.
- IDE para Java con Lombok (opcional).

- Postman (Para realizar las pruebas).

## Instalación y ejecución

Clonar este repositorio mediante el comando:

`git clone https://github.com/nicmora/weapp.git`

Para continuar elegir una de las siguientes opciones:

Opción A:
Importar el proyecto desde algún IDE (Eclipse/Spring Tool Suite, IntelliJ o similar) con soporte para [Lombok](https://projectlombok.org/).
Luego ejecutar la aplicación como "Java Application" desde el entorno de desarrollo.

Opción B
Abrir una terminal. Asegurarse de estar en el  directorio del proyecto. Luego, ejecutar el comando:

`mvn clean package`

Una vez realizada la compilación exitosamente. Dirigirse a la carpeta ***/target*** dentro del proyecto y ejecutar el comando:

`java -jar weapp-1.0.0`

Realizar pruebas con un software como Postman. Se agregó al repositorio la coleccioón de API request para correr con dicho programa.
Asegurarse de ingresar los ***timestaps***  (input de la request) actualizados y en el formato correcto (**"dd/MM/yyyy HH:mm:ss"**).

## Supuestos y Decisiones

Se agregó un endpoint "getAll" para obtener el listado de registros de la base de datos en memoria. Esto no es un requerimiento de usuario, pero facilita las pruebas al conocer los datos existentes.

Las validaciones de los inputs se realiza mediante anotaciones. Tanto para errores del tipo "Not Null" y "Formato del Timestamp"

Se agregó un manejador de excepciones del controller (Handler Exception). Existen otras fuera del controller, que gestiona el framework de Spring. Estas últimas no se tuvieron en cuenta.

Los Javadocs se encuentran en las interfaces de los servicios.

El método que calcula el average y diferencia porcentual con respecto al máximo, se podría haber resuelto en gran parte desde una consulta en el repositorio. Por ejemplo, utilizando:

@Query(value = "SELECT AVG(c.lprice) as average, MAX(c.lprice) as max FROM Coin c WHERE timestamp >= :timestampOne and timestamp <= :timestampTwo GROUP BY c.curr1, c.curr2", nativeQuery = true)
AvgMaxDTO getAvgAndMaxByTimestampBetween(@Param("timestampOne") LocalDateTime timestampOne, @Param("timestampTwo") LocalDateTime timestampTwo);

Se tomó la decisión de traer la lista de registros y realizar los calculos utilizando los métodos y funciones que ofrece Flux/Mono/Streams, que es un concepto a evaluar según el enunciado.

