# Use the official OpenJDK 22 image
FROM eclipse-temurin:22-jdk-alpine

# Set working directory
WORKDIR /app

# Copy all files
COPY . .

# Build the Spring Boot app
# Use Maven wrapper if present
RUN ./mvnw clean package -DskipTests

# Expose the port your app runs on
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/notes-backend-0.0.1-SNAPSHOT.jar"]
