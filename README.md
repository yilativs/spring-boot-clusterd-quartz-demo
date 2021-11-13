# Spring Boot Quartz Integration Demo

### Reference Documentation
Project shows how to create a **SpringBoot** service that runs **Quartz Scheduler** in persistent and clustered mode.

Service starts randomly on ports 8000-8010 (in order to validate that job is executed one at a time for the whole cluster.

A demo job writes instance id, job group name and job name to a table.

For persistence storage **PostgeSQL** is used.

In order to run service create a postgres user 

	create user demo with password 'demoSecret';

and database 

	create database quartz_demo with owner demo;

Requred tables will be created by **Flyway** migrations.