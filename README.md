# LOVE PDF

This project was generated with [SpringBoot] version 3.3.3 and [JAVA] version 21.

## Development server

Change mongo database credentials in application.properties, then run `./gradlew bootRun` for a dev server.

## Deploy in Docker

Open the root project folder, then run `./gradlew build`, after that, run `docker-compose up`. If any change is done, you must run `docker-compose down` and remove the love-pdf-api-image created, 
then re-build the project and run `docker-compose up` in order to see the changes.

To get more help on the SpringBoot use go check out the [SpringBoot Getting Started Guide](https://github.com/spring-guides/gs-spring-boot.git) page.

### Documentation

Project documentation is avaiable on [Love PDF Documentation](https://documenter.getpostman.com/view/31927537/2sAXxJgZhW)
