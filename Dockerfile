FROM openjdk:17
COPY target/lciii-scaffolding-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
