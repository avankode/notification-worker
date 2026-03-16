FROM gradle:8-jdk17 AS builder
WORKDIR /app

# Copy the Gradle config and source code
COPY build.gradle settings.gradle ./
COPY src ./src

# Build the JAR, skipping tests to speed up cloud deployment
RUN gradle clean build -x test

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app


COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]