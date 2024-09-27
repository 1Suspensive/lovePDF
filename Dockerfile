FROM eclipse-temurin:21-alpine

EXPOSE 8080

WORKDIR /root

COPY ./build/libs/lovePDFNonReactive-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]