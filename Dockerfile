# Use Maven + JDK 22 Alpine image
FROM maven:3.9.2-eclipse-temurin-22-alpine AS build

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Build the Spring Boot app (skip tests for faster build)
RUN mvn clean package -DskipTests

# ---- Stage 2: Minimal runtime image ----
FROM eclipse-temurin:22-jdk-alpine

WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=build /app/target/notes-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on (adjust if different)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java","-jar","app.jar"]
