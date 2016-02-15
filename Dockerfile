FROM gcr.io/google_appengine/openjdk8
VOLUME /tmp

ENV SPRING_PROFILES_ACTIVE appengine,appengine-secrets,production

ADD platform-server/build/libs/platform-server.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
