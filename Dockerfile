FROM maven:3.8.5-openjdk-8-slim
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn package
RUN ls
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/clientsnttpf-0.0.1-SNAPSHOT.jar"]
