# Usa una imagen oficial de Java 17
FROM eclipse-temurin:17-jdk

# Crea el directorio de trabajo
WORKDIR /app

# Copia el jar generado desde la carpeta api
COPY api/target/api-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto por defecto de Spring Boot
EXPOSE 8080

# Ejecuta la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
