FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/owlet-status.jar /owlet-status/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/owlet-status/app.jar"]
