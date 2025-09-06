# Etapa 1: build da aplicação
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace

# Copia o pom e resolve dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código e compila
COPY src src
RUN mvn package -DskipTests

# Etapa 2: imagem final
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia o JAR gerado
COPY --from=build /workspace/target/quarkus-app /app/quarkus-app

EXPOSE 8080

# Inicia a aplicação
CMD ["java", "-jar", "/app/quarkus-app/quarkus-run.jar"]
