FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/refactored-finance-app-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]