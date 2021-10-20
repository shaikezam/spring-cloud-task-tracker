FROM alpine:edge
RUN apk add --no-cache openjdk8
RUN apk --no-cache add curl
COPY target/*.jar /target/task-tracker-0.0.1-SNAPSHOT.jar
EXPOSE 80
WORKDIR /target
CMD ["java", "-jar", "task-tracker-0.0.1-SNAPSHOT.jar"]
