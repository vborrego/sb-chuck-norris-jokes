FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Get dependencies based on pom.xml
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2/repository mvn dependency:go-offline -B

# compile and package 
COPY src ./src
RUN --mount=type=cache,target=/root/.m2/repository mvn package -DskipTests -B

ENV SERVER_SSL_KEY_STORE_PASSWORD=123456
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-server:5432/mydb
ENV SPRING_DATASOURCE_USERNAME=userx
ENV SPRING_DATASOURCE_PASSWORD=passx

EXPOSE 8443 

# Run app
CMD ["java","-jar","/app/target/chucknorris-0.5.3.jar"]
