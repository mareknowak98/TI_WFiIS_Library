FROM openjdk:11
VOLUME /tmp
ADD library_backend/target/library-app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "java", "-jar", "/app.jar" ]