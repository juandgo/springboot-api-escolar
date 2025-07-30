#divide en capas para reducir el tamaño de la imagen final, se llama build, por eso hay 2 FROM
#Etapa 1: Contrucción del JAR
#Dockerfile esta dividido en 2 capas
#FROM maven:3.9.10-openjdk-17 AS build
FROM maven:3.9.6-eclipse-temurin-21 AS build


WORKDIR /app
COPY pom.xml ./
#RUN mvn dependency:go-offline

#Compila el codigo y crea el archivo .jar
COPY src ./src
#RUN mvn clean package -DskipTests
RUN mvn clean package -DskipTests -e -X


RUN ls -la /app/target

#Etapa 2: Construcción de la imagen final
#FROM openjdk:17-jdk-alpine
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/api-registro-escolar-0.0.1-SNAPSHOT.jar /app/api-registro-escolar.jar
#COPY --from=build /app/target/*.jar /app/app.jar

COPY wait-for-it.sh /app/wait-for-it.sh
RUN chmod +x /app/wait-for-it.sh

EXPOSE 8080

#ENTRYPOINT ["java","-jar","/app/api-registro-escolar.jar"]
#Esto se conoce como multistage build
