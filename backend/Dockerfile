FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/*.jar
EXPOSE 8080

ENV DB_URL=jdbc:postgresql://database:5432/3D_house_creator_db
ENV DB_USER=postgres
ENV DB_PASS=root
ENV DB_URL_DEV=jdbc:postgresql://localhost:5432/3D_house_creator_db

CMD ["java", "-jar", "/app/*.jar"]