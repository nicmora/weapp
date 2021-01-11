# Weapp - Wenance Challenge

La función principal de esta aplicación es conocer el precio del Bitcoin (BTC) con respecto al dolar (USD) en tiempo real, y realizar cálculos para obtener el valor promedio y variación de la criptomoneda en cierto rango de tiempo.

Para lograr esto se debe consumir el servicio brindado por la API:

[https://cex.io/api/last_price/BTC/USD](https://cex.io/api/last_price/BTC/USD)

## Requerimientos

- JDK 1.8.
- Maven 3.x.
- IDE para Java con Lombok (opcional).

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
