FROM openjdk:8-jdk-alpine
RUN addgroup -S restapp && adduser -S cawastore -G restapp
USER cawastore:restapp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]