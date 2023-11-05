#
# Build stage
#
FROM maven:3.9.4-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17
COPY --from=build /home/app/target/paperless-rest-1.0.jar /usr/local/lib/paperless-rest.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "/usr/local/lib/paperless-rest.jar"]
