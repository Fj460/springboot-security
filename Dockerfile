FROM openjdk:17
COPY ./target/Dockerize-0.0.1-SNAPSHOT.jar /tmp
COPY src /home/app/src
COPY pom.xml /home/app
COPY src/main/resources/application.properties /home/app/properties
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3303/dock
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=my-secret-password
ENTRYPOINT ["java", "-jar", "/tmp/Dockerize-0.0.1-SNAPSHOT.jar"]