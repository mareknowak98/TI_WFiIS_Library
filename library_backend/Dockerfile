FROM openjdk:11
VOLUME /tmp
ADD target/library-app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5000
ENTRYPOINT [ "java", "-jar", "/app.jar" ]