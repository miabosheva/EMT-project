# Added: v2
FROM maven:3.8.4-openjdk-11-slim AS builder

# Set the working directory in the container
WORKDIR /app

# Added: v2
RUN mvn clean install

# Copy the application JAR file into the container
COPY target/*.jar /app/app.jar

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]
