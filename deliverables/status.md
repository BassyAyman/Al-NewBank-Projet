# Weekly Status (Week 41) :

## What was done:

* Implementation of some first service interacting in order to validate a transaction
* new architecture at the database level, master-slave intercation for less complexity
* architecture change when it comes to transaction that has been validate, in order to be more resilient

## What is planned

* For Tuesday, have the scenario ready with a high number of transaction made

## What's the difference from last week

* The architecture
* The code

## Issues : 

* None

## Risk : 

*None

## RYG flag : 

  * Yellow : At the architecture level, its ok, we know where we are going, and we have all the step that we are going to follow in order to create a good POC and to demonstrate that it's a the good one, using high number of transaction in order to test every kind of implementation that we would had before. So at the end we have a good architecture and we have data about the other ones that wasn't that good with some test as proof. BUT, at the implementation level, we have some services well implemented but its the second week were we started implementing, and we still dont have any test of high level number of transaction in order to test a naive implementation which is : Xnumberclient -> transactionService -> BD -> transactionService -> validationOK/KO. We are a bit late. But we started anticipate the second test which gonna include a load balancer, as a member of the party is currently implementing it.
