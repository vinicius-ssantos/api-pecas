# Usa a imagem do JDK 17
FROM openjdk:17-jdk-slim
# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos da aplicação para o contêiner
COPY target/*.jar app.jar

# Expõe a porta 8088 para acesso externo
EXPOSE 8088

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]