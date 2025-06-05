

FROM maven as mavenbuild

WORKDIR /app

COPY ./api .

RUN mvn compile 

RUN mvn clean package

FROM openjdk:21

WORKDIR /app

COPY --from=mavenbuild /app/target/App-0.0.1-SNAPSHOT.jar /app.jar

CMD ["/usr/java/openjdk-21/bin/java", "-jar", "/app.jar"]
