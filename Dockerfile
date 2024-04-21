FROM openjdk:8-alpine

COPY target/uberjar/web-app-beta.jar /web-app-beta/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/web-app-beta/app.jar"]
