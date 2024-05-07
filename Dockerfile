#FROM openjdk:11-jre-slim
#
#WORKDIR /app
#COPY target/achat-1.0.jar /app/achat.jar
#CMD ["java", "-jar", "achat.jar"]
FROM openjdk:17-jre-slim
# Installation du client MySQL
RUN apt-get update && apt-get install -y default-mysql-client

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copie du jar de l'application dans le conteneur
COPY target/achat-1.0.jar /app/achat.jar

# Commande pour démarrer l'application
CMD ["java", "-jar", "achat.jar"]

