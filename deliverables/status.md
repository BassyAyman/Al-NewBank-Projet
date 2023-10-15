# Weekly Status (Week 40) :

## What was done:

* Modification on the service diagram 
* scenarios of implementation to test limit based on the V7 subject : Scalability
* CQRS solution modele added to the service diagram

## What is planned

* For Tuesday, have the implementation of the first Scenario ready

## What's the difference from last week

* The architecture : we have modified how we manage read and write with database into a CQRS model.

## Issues : 

* None

## Risk : 

* Having a bad interpretation of the CQRS in our modele context

## RYG flag : 

* Green : For this week, we know that we are following the good path in order to have a functionnal
Neo-bank that is ok with our Version of it. And even if our suggestion about CQRS is not the good one,
we will have an implementation of a first naive version that will demonstrate that in the case of a
large scale system (that can handle mutch more request at the same time), the naive solution have issue
and based on them, we will validate our supposition and solution.
