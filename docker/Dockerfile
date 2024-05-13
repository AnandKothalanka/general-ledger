# Stage 1: Build the Spring Boot application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /ledger-posting-system
COPY . .
WORKDIR /ledger-posting-system
RUN mvn clean install -DskipTests

# Stage 2: Create a lightweight Java image with the compiled artifact
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /ledger-posting-system/target/ledger-posting-system-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
