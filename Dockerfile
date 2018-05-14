FROM openjdk:8-jre-alpine

CMD java -jar /app.war

EXPOSE 8080

ADD target/*.war /app.war
