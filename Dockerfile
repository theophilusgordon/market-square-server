FROM spring-boot:3

WORKDIR /usr/src/app

COPY . .

EXPOSE 8080

CMD ["mvn", "app.jar"]
