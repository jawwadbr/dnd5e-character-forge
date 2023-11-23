# Getting Started

## How to run

### Locally

* Build the project:
```
./mvnw clean package
```
* Execute the project 
```
java -jar target/dnd5e-back-0.0.1-SNAPSHOT.jar
```

The API will be available at [localhost:8080](http://localhost:8080).

### Using [Docker](https://www.docker.com)

* Build the project:
```
./mvnw clean package
```

* Run the Docker Compose
```
docker-compose -f docker-compose.yml up -d
```

The API will be available at [localhost:8080](http://localhost:8080).

#### Stopping Docker Container

* To stop the container, first, find the container ID by running this command.
```
docker ps
```

* Stop container using its ID
```
docker stop <container_id>
```
