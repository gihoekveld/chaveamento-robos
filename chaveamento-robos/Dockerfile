# build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# baixa as dependencias numa camada separada, so refeita quando o pom muda
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY src ./src
RUN mvn -B clean package -DskipTests

# runtime
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Fly.io encaminha o trafego para a porta 8080 configurada em fly.toml.
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
