FROM eclipse-temurin:17-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todo el contenido del proyecto (incluye pom.xml, src/, mvnw, etc.)
COPY . .

# Da permisos de ejecución al wrapper de Maven
RUN chmod +x ./mvnw

# Compila el proyecto y genera el JAR
RUN ./mvnw clean package -DskipTests

# Ejecuta la aplicación Spring Boot
CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]
