FROM amazoncorretto:8
EXPOSE 8080
ADD target/weather-forecast-docker.jar weather-forecast-docker.jar
ENTRYPOINT ["java","-jar","/weather-forecast-docker.jar"]
