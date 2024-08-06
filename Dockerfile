FROM openjdk:17
COPY ./build/libs/carbofootprint-0.0.1-SNAPSHOT.jar carbofootprint.jar
ENTRYPOINT ["java", "-jar", "carbofootprint.jar"]
