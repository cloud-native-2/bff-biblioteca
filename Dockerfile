# Build
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY . .

RUN (command -v dos2unix >/dev/null 2>&1 && dos2unix mvnw) || sed -i 's/\r$//' mvnw \
    && chmod +x mvnw \
    && ./mvnw -v

# Ejecuta mvn clean package sin correr los tests
RUN ./mvnw clean package -DskipTests


FROM eclipse-temurin:21-jre

# Crea directorio de la app
WORKDIR /app

# Copia el JAR de tu microservicio (asegúrate que el nombre coincida)
COPY --from=builder /app/target/*.jar app.jar

#COPY src/main/resources/BD/wallet /app/wallet
#
## Variables entorno
#ENV WALLET_TNS="/app/wallet"
#ENV ORACLE_DB_USER=""
#ENV ORACLE_DB_PASS=""

# Expone el puerto (si tu microservicio usa el 8080 por ejemplo)
EXPOSE 8080


# Comando para correr la app
ENTRYPOINT ["java", "-jar", "app.jar"]