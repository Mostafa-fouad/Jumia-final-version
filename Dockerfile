FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD Jumia-Custom-Filter-App/jumia-exercise-0.0.1-SNAPSHOT.jar app.jar
ADD Jumia-Custom-Filter-App/sample.db sample.db
ENTRYPOINT ["java","-jar","/app.jar"]
