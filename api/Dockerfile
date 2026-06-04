FROM mcr.microsoft.com/openjdk/jdk:21-mariner AS build
COPY . .
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

FROM mcr.microsoft.com/openjdk/jre:21-mariner
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]