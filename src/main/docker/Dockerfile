FROM openjdk:8-jdk-alpine
LABEL maintainer="Cristian Urbainski"

EXPOSE 8082

RUN mkdir -p /app/
RUN mkdir -p /app/logs/

ADD  target/veiculo-api-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker", "-jar", "/app/app.jar"]