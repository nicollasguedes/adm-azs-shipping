FROM maven:3.8.5-openjdk-17 AS builder
# Use maven image with OpenJDK 17 as build environment
WORKDIR /app
# Set working directory to /app
COPY pom.xml .
# Copy pom.xml to /app in builder stage
RUN mvn dependency:go-offline
# Download dependencies and store them offline (avoids downloading on subsequent builds)
COPY src ./src
# Copy source code directory (src) to /app
RUN mvn package -DskipTests
# Build the application and generate JAR in /app/target


FROM openjdk:17-alpine
# Use openjdk:17-alpine image as the base for the final image
WORKDIR /app
# Set working directory to /app in runtime stage
COPY --from=builder /app/target/*.jar app.jar
# Copy JAR file from builder stage to /app and rename it to app.jar in runtime stage
ENTRYPOINT ["java", "-jar", "app.jar"]
# Set the default command to run the application (java -jar app.jar)
HEALTHCHECK --interval=10s --timeout=3s CMD curl --fail http://localhost:${SPRING_DOCKER_PORT}/actuator/health || exit 1
# Defines a health check for the container. It configures a health check to run every 10 seconds (with a timeout of 3 seconds)