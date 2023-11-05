# Weekly Status (Week 44) :

## What was done:

* Implementation of more busness on transaction validation side
* Implementation of new write and read service
* Implementation of a master slave relation database
* Implementation of load balancer in front of transaction service 

## What is planned

* Implementation cache and logic related in the write and read service
* Implementation of what come after a transaction succed ( write impact on DB using kafka )
* Probably migrate Read service to Spring Web Flux

## What's the difference from last week

* The architecture
* The code

## Issues : 

* We couldn't go step by step in our architecture contruction, we planned to build step by step our thing and at each step test the limit to have kind a
  historic of what we did to demonstrate it to legitimate our actual architecture as propably one of the "best" in terme of choice to response to our
  problematique in this banc contexte.

## Risk : 

*To take more time on the cache configuration, we will use redis which is well documented but we never know

## RYG flag : 

  * Yellow : In architecture termes, we have something that for us is good and will not change in terme of database management.
    In terme of implementation, We ended up doing a lot of configuration work in the short time we had off, but it took us a long time, and indeed it did. We still      have a few things to work out before we can put it all together and have a walking skeleton.
