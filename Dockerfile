

FROM gradle:jdk21 as gradlebuild

WORKDIR /app

COPY ./api .

RUN ./gradlew build

FROM openjdk:21

WORKDIR /app

COPY --from=gradlebuild /app/build/libs/*.jar /app.jar

CMD ["/usr/java/openjdk-21/bin/java", "-jar", "/app.jar"]
