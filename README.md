# AMT Project One

## Description

Simple multi-tiered application in Java EE for AMT 2019 course at HEIG-VD.
You can find the guidelines repo [here](https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-AMT-2019-Project-One)

Our application is about travelling. It allows users to record the cities they have visited in the world
and the dates of the beginning and end of their stay.

## Specification

Features:

- Users will be able to create an account and log in.
- When they log in, they will see their visited cities.
- Here, they can add a new stay (a city, a start date and an end date) using a button.
- By clicking on a city, they can see the details of their different stays there.
- Here, they can remove a stay or update its information.
- By clicking on the "Top cities" button, they'll be able to see the most visited cities.

You can find the initial mockup of our application [here](specification/mockup.pdf).

You can find our database relational model [here](specification/db-model.png).

## Run the server

### With docker

With `docker-compose`, launching the server is as easy as a single command.
Make sure you are in the `docker` folder and run:

```
docker-compose up
```

This will launch both a database container and an application server for the app.
It will also populate the database with countries and cities, and (TODO) generate some test users and visits.

You can then access

- the website at [http://localhost:8080/citylog](http://localhost:8080/citylog)
- the admin console at [http://localhost:4848](http://localhost:4848) (user: admin / password: admin)

### Local setup with Payara

- Download the full Payara application server (version 5) on their website.
- Copy the mysql driver `docker/payara/mysql-connector-java-8.0.18.jar` in `payara5/glassfish/domains/domain1/lib`.
- In the folder `payara5/glassfish/bin`, run the following commands to create the jdbc resource pool.

```
./asadmin start-domain domain1

./asadmin create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc
.MysqlDataSource --property 'user=<username>:password=<password>:url=jdbc\:mysql\://localhost\:3306/citylogdb:useSSL
=false' citylog_pool

./asadmin create-jdbc-resource --connectionpoolid citylog_pool jdbc/citylogdb

./asadmin stop-domain domain1
```

- Run the sql scripts in your local database to create the db.
- Setup the deployment of the `.war` in your favourite IDE.

## Tests

### Test servlets

## Load testing experiment

Benchmark for pagination between the business and the resources tier...
