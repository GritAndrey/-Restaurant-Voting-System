# Restaurant voting system

Design and implement a REST API using Hibernate/Spring/SpringMVC without frontend.

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/b3899be1a1ee4057b1a22c9aee7a78fa)](https://www.codacy.com/gh/GritAndrey/restaurant-voting-system/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=GritAndrey/restaurant-voting-system&amp;utm_campaign=Badge_Grade)

A voting system for deciding where to have lunch.

- 2 types of users: admin and regular users
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a food name and price)
- Menu changes each day (admins do the updates)
- Users can vote on which restaurant they want to have lunch at
- Only one vote counted per user
- If user vote again the same day:
- If it is before 11:00 we assume that he changed his mind.
- If it is after 11:00 then it is too late, vote can't be changed
- Each restaurant provides a new menu each day.

## Stack

JDK 17, Spring Boot 2.7, Lombok, H2, Querydsl, Caffeine Cache, Swagger/OpenAPI 3.0

## Launch methods.

### Local

- Run```mvn spring-boot:run``` in project root directory.
- Open [localhost](http://localhost:8080/)

### Docker

```shell
mvn clean package
```

- Build:

```shell
docker build -t voting .
```

- Run:

```shell
docker run --name voting -p 8080:8080 voting:latest
```

- Open [localhost](http://localhost:8080/)

### Docker Compose

```shell
mvn clean package
```

```shell
docker-compose build 
```

```shell
docker-compose up
```

Open [localhost](http://localhost:8080/)

## Testing credentials.

| email | password |
| ------ | ------ |
| user@gmail.com | password |
| admin@gmail.com | admin |

## API documentation

- [api-docs](http://localhost:8080/v3/api-docs/REST%20API) - Api docs
- [swagger](http://localhost:8080/swagger-ui/index.html) - Swagger ui