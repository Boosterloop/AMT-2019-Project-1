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

In order to run the server, you first have to package the sources in a `.war`.

Thanks to Apache Maven, this is just a single command away. Position yourself at the root of the project 
(where the `src` dir is), and run:

```
mvn install
```

### With docker

With `docker-compose`, launching the server is as easy as a single command.
Make sure you are in the `docker` folder and run:

```
docker-compose up
```

This will launch both a database container and an application server for the app.
It will also populate the database with countries and cities, and generate some test users and visits.

If you want to populate the database with millions of visits, please uncompress the `docker/mysql/get-data/visits.tar
.gz` file in the same folder and THEN run `docker-compose`.

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

Test users:

- luc, paSSw0rd
- alison, 413F5tp.$
- admin, Sidney413

The first two users have a list of visits created in the test data.

The third one doesn't have any visits by default.

### Test servlets

_Sadly, we missed the window to write clean servlet tests using mockito_

### Test DAOs

We use Arquillian to test DAOs.

In order to run the tests correctly, it is necessary to:

- Run the `import.sh` script in the `ssl` directory after making sure the paths in the file are right.
- Run the database and application server in docker (see the manual higher in this readme).
- **Undeploy** the application on the [admin page of the server](http://localhost:4848).
- Run `mvn clean install` and check the tests results.

_Sadly, we were only able to test one DAO, and with simple tests._

## Load testing experiment

Benchmark for pagination between the business and the resources tier...

_We sadly missed the opportunity to achieve anything useful in this part of the lab._

## Known issues

- After updating or deleting an existing visit the user should be redirected to /visits but it does not happen.
