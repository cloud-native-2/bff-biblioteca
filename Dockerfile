# Build
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .

RUN sed -i 's/\r$//' mvnw && chmod +x mvnw

RUN ./mvnw clean package -DskipTests

# Runtime
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]