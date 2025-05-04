FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /workspace

COPY pom.xml .

COPY src src

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /workspace/target/bbm-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]