# projectjsf
 proyecto documentos


# Instalación
requiere [docker](https://www.docker.com/) to run.
en la raiz del proyecto:
```sh
$ mvn install
$ docker-compose up --build
```
automaticamente creará la base de datos y desplegara el proyecto en tomcat

# NOTA
en hibernate.cfg.xhtml se cambio la url jdbc:postgresql://postgresdb:5432/documento para que se conecte con el servicio de postgresql en docker-compose
si se desea probar sin docker, cambiar la linea a jdbc:postgresql://localhost:5432/documento y ejecutar el script int.sql para crear las tablas en la base de datos local
