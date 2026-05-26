
FROM openjdk:11
WORKDIR /app
COPY target/demo-workshop-2.1.2.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
