# Estágio 1: Build da aplicação
FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Imagem final mais enxuta
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/app.jar ./app.jar



# Expõe a porta da aplicação
EXPOSE 8088

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
