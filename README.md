### ENV VARIABLES
#### Local run
- SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3308/event_reminder_db
#### Docker-compose run
- SPRING_DATASOURCE_URL=jdbc:mysql://mysql-development:3306/event_reminder_db?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false

### HOW TO BUILD
#### Docker-compose
```
gradle clean build
```
COPY build/libs/event_reminder-0.0.1-SNAPSHOT.jar TO docker/db-docker

```agsl
docker-compose build
docker-compose up
```
