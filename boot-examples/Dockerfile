FROM arm64v8/eclipse-temurin:20-jre
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
