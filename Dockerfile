FROM openjdk:20-slim

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /app/method_name_generator.jar

ENV SPRING_PROFILES_ACTIVE=prod

CMD ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "method_name_generator.jar"]
