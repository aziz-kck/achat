#FROM openjdk:11-jre-slim
#
#WORKDIR /app
#COPY target/achat-1.0.jar /app/achat.jar
#CMD ["java", "-jar", "achat.jar"]
#FROM openjdk:11-jre-slim
FROM eclipse-temurin:17-jdk-jammy

# Installation du client MySQL
RUN apt-get update && apt-get install -y default-mysql-client

# Répertoire de travail dans le conteneur
WORKDIR /app
ADD https://github.com/aziz-kck/achat/raw/aziz/target/achat-1.0.jar /app/achat.jar
#test
# Copie du jar de l'application dans le conteneur
#COPY target/achat-1.0.jar /app/achat.jar
# Commande pour démarrer l'application
CMD ["java", "-jar", "achat.jar"]
