# ---- Stage 1: Build ----
FROM maven:3.9.2-amazoncorretto-22 AS build

WORKDIR /app
COPY . .

# Build Spring Boot app
RUN mvn clean package -DskipTests

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:22-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/notes-backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
