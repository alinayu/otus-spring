FROM java:8-jdk-alpine
COPY ./target/homework-ajax-rest-1.0-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch homework-ajax-rest-1.0-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","homework-ajax-rest-1.0-SNAPSHOT.jar"]