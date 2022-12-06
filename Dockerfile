FROM openjdk:11
EXPOSE 1000
ADD target/user-service-docker.jar user-service-docker.jar
ENTRYPOINT ["java","-jar","/user-service-docker.jar"]