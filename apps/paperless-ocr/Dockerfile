# Build stage
FROM maven:3.9.4-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Intermediate stage for installing Tesseract OCR and language pack
FROM openjdk:8-jre-alpine AS intermediate
RUN apk update
RUN apk add --no-cache tesseract-ocr
RUN mkdir -p /usr/share/tessdata
ADD https://github.com/tesseract-ocr/tessdata/blob/main/deu.traineddata /usr/share/tessdata/deu.traineddata

# Final stage
FROM openjdk:17
COPY --from=build /home/app/target/paperless-ocr-1.0.jar /usr/local/lib/paperless-ocr.jar
COPY --from=intermediate /usr/share/tessdata/deu.traineddata /usr/share/tessdata/deu.traineddata
COPY test.txt /usr/share/tessdata
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/usr/local/lib/paperless-ocr.jar"]
