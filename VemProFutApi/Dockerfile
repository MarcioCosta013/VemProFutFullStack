# Utilizei uma abordagem de múltiplos estágios (multi-stage build), que gera uma imagem final muito menor e mais segura.
# Estágio 1: Build da aplicação com Maven e JDK 21
FROM maven:3.9-eclipse-temurin-21 AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia o pom.xml para baixar as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o restante do código-fonte e constrói o JAR
COPY src ./src
RUN mvn clean install -DskipTests

# Estágio 2: Criação da imagem final com JRE
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/VemProFut-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
