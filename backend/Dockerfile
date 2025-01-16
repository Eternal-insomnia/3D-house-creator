FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/*.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/*.jar"]