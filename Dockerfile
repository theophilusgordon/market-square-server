FROM spring-boot:3

WORKDIR /usr/src/app

COPY . .

RUN mvn clean install

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
