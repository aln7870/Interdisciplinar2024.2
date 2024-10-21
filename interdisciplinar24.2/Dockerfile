# Use uma imagem do OpenJDK 20 como base
FROM openjdk:20-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Adicione o JAR da aplicação ao contêiner
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Expõe a porta que a aplicação Spring Boot usará
EXPOSE 8080

# Diga ao contêiner para executar a aplicação
ENTRYPOINT ["java","-jar","/app/app.jar"]
