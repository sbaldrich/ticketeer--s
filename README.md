## The Ticketeer Project

[![Build Status](https://travis-ci.org/sbaldrich/ticketeer-microservices.svg?branch=master)](https://travis-ci.org/sbaldrich/ticketeer-microservices)

The Ticketeer project implemented as microservices that register themselves to an Eureka service registry and communicate via REST endpoints.

#### Running Ticketeer

Run the following main classes in order: `RegistrationServer`, `AccountsMicroservice` and `TicketsMicroservice`.

#### How to use

List available events by sending a `GET` request to the ticket service (`localhost:3333` by default). Send a purchase request by `POST`ing to the `/purchase` endpoint with an payment request such as `{"user" : "1", "event" : "1"}`.

#### Issues

This is a quick and dirty implementation. Of course there are ~~many~~ issues.
