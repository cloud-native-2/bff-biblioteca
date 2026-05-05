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

ENV EVENT_GRID_KEY="El4Zp7GXv5O6jjJeOuxdmHZquYE7O2SRLLlhOCMdqt0xSENpkCb6JQQJ99CEACYeBjFXJ3w3AAABAZEGZMcA"

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]