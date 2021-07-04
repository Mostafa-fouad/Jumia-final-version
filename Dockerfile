FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD target/jumia-exercise-0.0.1-SNAPSHOT.jar app.jar
ADD target/sample.db sample.db
ENTRYPOINT ["java","-jar","/app.jar"]
