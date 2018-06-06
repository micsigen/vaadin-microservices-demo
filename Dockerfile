# BUILD
FROM openjdk:8u131-jdk-alpine

RUN MAVEN_VERSION=3.5.0 \
 && cd /usr/share \
 && wget http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz -O - | tar xzf - \
 && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

WORKDIR /home/lab

COPY admin-application/pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY biz-application/pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY config-server/pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY discovery-server/pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY monitor-application/pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY news-application/pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY proxy-server/pom.xml .
RUN mvn verify -DskipTests --fail-never

COPY website-application/pom.xml .
RUN mvn verify -DskipTests --fail-never