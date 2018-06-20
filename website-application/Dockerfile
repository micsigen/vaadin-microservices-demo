# BUILD
FROM micsigen/vaadin-microservice-build:1.0-SNAPSHOT as build

WORKDIR /home/lab

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

# RUN
FROM openjdk:8u131-jdk-alpine
ENV LANG C.UTF-8

ENTRYPOINT ["java", "-Xmx512m", "-Xms512m", "-jar", "./target/website-application-0.0.1-SNAPSHOT.jar"]

COPY --from=build /home/lab/target ./target/