FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY target/RestaurantVotingSystem-1.0.jar .
ENTRYPOINT [ "java", "-jar", "RestaurantVotingSystem-1.0.jar"]