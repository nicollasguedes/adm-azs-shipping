
# adm-azs-shipping

Projeto desafio AzShip que consiste em criar uma API para sistema de gestao fretes. Essa gestão consiste basicamente em um CRUD das informações do frete.




## Objectives

Create a system that manages freight information. The application should provide a REST or GraphQL API with the following operations:

● Register Shipment;

● Search Shipment (the search should only receive one parameter that should be used to search in all freight properties, and it should be paginated)

● Update Shipment Data

● Remove Shipment

# Requirements

## Localhost Usage

Before running the application on your local machine, ensure you have the following prerequisites:

- [Java Development Kit (JDK)](https://adoptopenjdk.net/) version 17 or later.
- [Apache Maven](https://maven.apache.org/) for building the project.
- [Docker](https://www.docker.com/) for containerization.

### Environment Variables

Find the `.env` file in the project root with the following environment variables:

```env
MYSQLDB_USER=your_mysql_username
MYSQLDB_PASSWORD=your_mysql_password
MYSQLDB_DATABASE=your_mysql_database
MYSQLDB_LOCAL_PORT=3306
MYSQLDB_DOCKER_PORT=3306
SPRING_LOCAL_PORT=8080
SPRING_DOCKER_PORT=8080
```


## Instalation

1. configure the .env file as you please:

```text
MYSQLDB_USER=your_mysql_username
MYSQLDB_PASSWORD=your_mysql_password
MYSQLDB_DATABASE=your_mysql_database
MYSQLDB_LOCAL_PORT=3306
MYSQLDB_DOCKER_PORT=3306
SPRING_LOCAL_PORT=8080
SPRING_DOCKER_PORT=8080

DB_URL=jdbc:mysql://${MYSQLDB_USER}:${MYSQLDB_PASSWORD}@mysqldb:${MYSQLDB_DOCKER_PORT}/${MYSQLDB_DATABASE}
```

2. Build the Docker container:

```bash
docker-compose build --no-cache
```

3. Start the container for both services:

```bash
docker-compose up
```

4. the default API URL:

```text
http://localhost:8080/api
```
