FROM openjdk:17
COPY ./target/Dockerize-0.0.1-SNAPSHOT.jar /tmp
COPY src /home/app/src
COPY pom.xml /home/app
COPY src/main/resources/application.properties /home/app/properties
ENV DATABASE_URL=jdbc:mysql://172.18.0.2:3306/dock
ENV DATABASE_USERNAME=root
ENV DATABASE_PASSWORD=dbpass
ENTRYPOINT ["java", "-jar", "/tmp/Dockerize-0.0.1-SNAPSHOT.jar"]