# First stage: complete build environment
FROM maven:3.9.0-amazoncorretto-11 AS builder

# add pom.xml and source code
ADD ./pom.xml pom.xml
ADD ./src src/

# package jar
RUN mvn clean package

# Second stage: minimal runtime environment
FROM amazoncorretto:11

# copy jar from the first stage
COPY --from=builder target/np-bot-jar-with-dependencies.jar app.jar
CMD ["java", "-jar", "/app.jar"]

