# Start from a lightweight JDK runtime
FROM eclipse-temurin:21-jre-alpine   # or 17-jre if you’re on Java 17

# Set the working directory inside the container
WORKDIR /app

# Copy the fat jar into the container
COPY target/myapp-0.0.1-SNAPSHOT.jar app.jar

# Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]
