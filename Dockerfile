FROM openjdk:17-jdk
VOLUME "/temp"
COPY target/market-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

