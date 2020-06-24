# POMODORO-API

[![Build Status](https://travis-ci.org/aoharkov/pomodoro-api.svg?branch=develop)](https://travis-ci.org/aoharkov/pomodoro-api)
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=aoharkov_pomodoro-api&metric=coverage)](https://sonarcloud.io/dashboard?id=aoharkov_pomodoro-api)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=aoharkov_pomodoro-api&metric=alert_status)](https://sonarcloud.io/dashboard?id=aoharkov_pomodoro-api)


### A short guide on how to use Docker container with PostgresSQL for running locally our application

1.	Using cmd go to the project root of your local repository
2.	Change image tag if needed to today date in docker-compose.yml:
``` image: pomodoro-db:dd.mm.yy ```
3.	Create and run a container from image with the following command: 
``` docker-compose up -d ```
4.	Now the container is running.
To run locally our application type ``` mvn spring-boot:run ```
To use locally our application go to  ``` http://localhost:8088/ ```
To stop locally our application use  ``` Ctrl + C ```
5.	If anything goes wrong, you may need to use the following commands:
To stop container: ``` docker kill pomodoro-db ``` 
To remove container: ``` docker rm pomodoro-db```
To remove image: ``` docker rmi pomodoro-db:dd.mm.yy ```
