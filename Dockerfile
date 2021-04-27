FROM openjdk:8
EXPOSE 8080
ADD target/mobilab-0.0.1-SNAPSHOT.jar mobilab-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mobilab-0.0.1-SNAPSHOT.jar"]