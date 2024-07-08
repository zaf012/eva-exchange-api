FROM openjdk:19-jdk-slim

WORKDIR /app

COPY target/*.jar evaexchangeapi.jar

EXPOSE 9080

ENTRYPOINT ["java","-jar","/app/evaexchangeapi.jar"]

