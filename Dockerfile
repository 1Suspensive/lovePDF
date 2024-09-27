FROM eclipse-temurin:17-alpine

EXPOSE 8080

WORKDIR /root

RUN apk update && apk add --no-cache \
    bash \
    libc6-compat \
    openjdk17-jdk

COPY ./build.gradle /root
COPY ./settings.gradle /root
COPY ./gradlew /root
COPY ./gradle /root/gradle

RUN chmod +x gradlew

RUN ./gradlew build --refresh-dependencies

COPY ./src /root/src

RUN ./gradlew build -x test

ENTRYPOINT ["java", "-jar", "/root/build/libs/lovePDFNonReactive-0.0.1-SNAPSHOT.jar"]