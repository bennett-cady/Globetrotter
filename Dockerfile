FROM maven:3.5.4-jdk-8-alpine AS builder

COPY src/ src/
COPY pom.xml pom.xml

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:8 AS runner

EXPOSE 8080

COPY --from=builder target/globetrot-0.0.1-SNAPSHOT.jar globetrot.jar

ENTRYPOINT [ "java", "-jar", "globetrot.jar" ]
