# BUILD
FROM micsigen/vaadin-microservice-build:1.0-SNAPSHOT as build

WORKDIR /home/lab

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

# RUN
FROM openjdk:8u131-jdk-alpine
ENV LANG C.UTF-8

RUN apk add --no-cache curl

ENTRYPOINT ["./start-server.sh", "http://discovery-server:8761/application/info"]

COPY --from=build /home/lab/target ./target/
COPY start-server.sh .