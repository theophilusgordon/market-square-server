FROM java:17

WORKDIR /usr/src/app

COPY . .

RUN mvn clean install

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "market-square-server-1.0.0.jar"]
