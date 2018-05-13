FROM openjdk:8-jre-alpine

CMD java -jar /app.jar

EXPOSE 8080

ADD target/*.jar /app.jar
