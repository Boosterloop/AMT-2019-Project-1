# AMT Project 1

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

- You can find the initial mockup of our application [here](specification/mockup.pdf).
- You can find our database relational model [here](specification/DBmodel.pdf).

## Run the server

Using `docker-compose`, launching the server is as easy as a single command.
Make sure you are in the main project folder (where the `Dockerfile is`) and run:

```
docker-compose up
```

This will launch both a database container and an application server for the app.

It will also populate the database with countries and cities. (TODO)

## Tests

### Test servlets

### Test UI

Selenium...

## Load testing experiment

Benchmark for pagination between the business and the resources tier...
