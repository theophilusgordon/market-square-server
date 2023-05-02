FROM maven:3.8.3-openjdk-17 as builder

WORKDIR /usr/src/app

COPY pom.xml .
COPY src ./src

RUN mvn clean install -DskipTests

FROM openjdk:17-slim

WORKDIR /usr/src/app

COPY --from=builder /usr/src/app/target/market-square-server-1.0.0.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "market-square-server-1.0.0.jar"]
