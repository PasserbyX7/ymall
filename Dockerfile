FROM openjdk:14
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD $PROJECT_NAME/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT exec java --enable-preview $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar --spring.profiles.active=dev
