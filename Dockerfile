

FROM gradle:jdk21 AS builder

WORKDIR /app

COPY ./api .

RUN ./gradlew build --no-daemon

RUN ls build/** && sleep 15


FROM eclipse-temurin:21

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar /app.jar

CMD ["/opt/java/openjdk/bin/java", "-jar", "/app.jar"]
