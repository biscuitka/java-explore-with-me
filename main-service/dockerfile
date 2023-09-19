FROM amazoncorretto:11-alpine-jdk
EXPOSE 8080
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]