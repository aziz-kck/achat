# Utilisation d'une image de base Java
FROM openjdk:11-jre-slim

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copie du jar de l'application dans le conteneur
COPY target/application-achat.jar /app/application-achat.jar

# Commande pour démarrer l'application
CMD ["java", "-jar", "application-achat.jar"]
