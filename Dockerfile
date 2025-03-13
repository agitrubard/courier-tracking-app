FROM maven:3.9.9-amazoncorretto-17-al2023 AS build

COPY pom.xml ./
COPY src src

RUN mvn clean install -DskipTests

FROM amazoncorretto:17.0.13-al2023-headless

WORKDIR /app

COPY --from=build target/*.jar /app/courirer-tracking-app.jar

EXPOSE 8988

ENTRYPOINT ["java", "-jar", "/app/courirer-tracking-app.jar"]
