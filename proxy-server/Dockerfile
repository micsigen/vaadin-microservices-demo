# BUILD
FROM micsigen/vaadin-microservice-build:1.0-SNAPSHOT as build

WORKDIR /home/lab

COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

# RUN
FROM alpine:edge
ENV LANG C.UTF-8
ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk/jre
ENV PATH $PATH:/usr/lib/jvm/java-1.8-openjdk/jre/bin:/usr/lib/jvm/java-1.8-openjdk/bin
RUN apk add --no-cache openjdk8-jre="8.161.12-r0" && rm usr/lib/libgif.so.7.0.0 usr/lib/libtasn1.so.6.5.5

ENTRYPOINT ["java", "-Xmx100m", "-Xms100m", "-jar", "./target/proxy-server-0.0.1-SNAPSHOT.jar"]

COPY --from=build /home/lab/target ./target/