Prerequisite:
-------------
= Having docker installed on your machine.

___________________________________________________________________________________________________________

Create Docker Image from Dockerfile:
------------------------------------
= First you should put the jar and the sample.db in the same folder (Jumia-Custom-Filter-App/jumia-exercise-0.0.1-SNAPSHOT.jar) & (Jumia-Custom-  Filter-App/sample.db)
  because i have configured the Dockerfile upon this structure. Then run the following command in the cmd after cd to the Dockerfile directory.

D:/jumia-exercise> docker build -t jumiatask .

___________________________________________________________________________________________________________

Run your docker container from docker image:
--------------------------------------------

> docker run -p8885:8080 jumiatask

___________________________________________________________________________________________________________

Run the app using the following url:
------------------------------------

localhost:8085/jumia/custom-filteration

====> The App is up and running, and you can validate the built web-app based on the requirements :)


