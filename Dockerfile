FROM spring-boot:3

WORKDIR /usr/src/app

COPY . .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
