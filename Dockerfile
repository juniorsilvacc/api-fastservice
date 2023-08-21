FROM openjdk:19-jdk-slim
WORKDIR /app
COPY target/api-fastservice-0.0.1-SNAPSHOT.jar /app/api-fastservice.jar
ENTRYPOINT ["java","-jar","api-fastservice.jar"]