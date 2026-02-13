

FROM gradle:jdk21 AS builder
WORKDIR /app

COPY api/gradlew .
COPY api/gradle gradle/
COPY api/build.gradle.kts api/settings.gradle.kts ./

RUN chmod +x gradlew
# build dependencies
RUN ./gradlew dependencies --no-daemon

COPY api/src src
RUN ./gradlew bootJar --no-daemon -x test
# optimisation pour docker
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../../build/libs/*.jar)

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
ARG DEPENDENCY=/app/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.arch.Application"]

