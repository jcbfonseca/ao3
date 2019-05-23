FROM openjdk:8
VOLUME /tmp
ADD target/HelloSpring-0.0.1-SNAPSHOT.jar HelloSpring-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","HelloSpring-0.0.1-SNAPSHOT.jar"]
