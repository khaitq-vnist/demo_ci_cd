# Build stage
FROM maven:3.9.4 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -Dmaven.test.failure.ignore=true

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/project3-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
