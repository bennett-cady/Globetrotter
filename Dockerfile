FROM maven:3.5.4-jdk-8-alpine AS builder



COPY pom.xml pom.xml
COPY src/ src/

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11 AS runner

ENV API_KEY=${API_KEY}

EXPOSE 8080

COPY --from=builder target/globetrot-0.0.1-SNAPSHOT.jar globetrot.jar



ENTRYPOINT [ "java", "-jar", "globetrot.jar" ]