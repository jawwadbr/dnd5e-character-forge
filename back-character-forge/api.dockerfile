FROM openjdk:17-alpine

WORKDIR /app

COPY target/dnd5e-back-character-forge-0.0.1-SNAPSHOT.jar /app/dnd5e-back-character-forge-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "dnd5e-back-character-forge-0.0.1-SNAPSHOT.jar"]
