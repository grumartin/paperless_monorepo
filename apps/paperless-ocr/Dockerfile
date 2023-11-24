#
# Build stage
#
FROM maven:3.9.4-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:17
COPY --from=build /home/app/target/paperless-ocr-1.0.jar /usr/local/lib/paperless-ocr.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/usr/local/lib/paperless-ocr.jar"]
