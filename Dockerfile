# Utilisation d'une image de base Java
FROM openjdk:11-jre-slim

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copie du jar de l'application dans le conteneur
COPY target/achat-1.0.jar /app/achat.jar

# Commande pour démarrer l'application
CMD ["java", "-jar", "achat.jar"]
#FROM eclipse-temurin:17-jdk-jammy
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN chmod +x mvnw
#RUN ./mvnw dependency:resolve
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]