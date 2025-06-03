#container file for podman
FROM openjdk:21

WORKDIR /app
#executable file
COPY app/target/src-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]



