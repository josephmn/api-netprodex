#FROM ubuntu:22.04 AS build
#RUN apt-get update
#RUN apt=get install openjdk-17-jdk -y
#COPY . .
#RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/api_netprodex-1.0.jar

# Definir variables de entorno
ENV DB_URL="jdbc:mysql://db_mysql:3306/netprodexDB?createDatabaseIfNotExist=true"
ENV DB_USER_NAME="root"
ENV DB_PASSWORD="password"

COPY ${JAR_FILE} api_netprodex.jar
EXPOSE 8060
ENTRYPOINT ["java", "-jar", "api_netprodex.jar"]

# # Usa una imagen base de OpenJDK 17
# FROM openjdk:17-jdk-slim
#
# # Establece el directorio de trabajo en /app
# WORKDIR /app
#
# # Copia el archivo build.gradle y settings.gradle al directorio de trabajo
# COPY build.gradle settings.gradle /app/
#
# # Copia todo el contenido de tu proyecto al directorio de trabajo
# COPY . .
#
# # Ejecuta la tarea Gradle para construir tu aplicación
# RUN ./gradlew build
#
# # Exponer el puerto en el que se ejecuta tu aplicación
# EXPOSE 8090
#
# # Comando para ejecutar tu aplicación cuando el contenedor se inicie
# CMD ["java", "-jar", "build/libs/api-netprodex-1.0.jar"]