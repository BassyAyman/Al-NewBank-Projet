# NewBank architecture project - v7

## Authors:
- Ayman Bassy
- Igor Melnyk
- Tobias Bonifay
- Mathieu Schalkwijk

## How to run
Use script `prepareAll.sh` to run the project. It will build all the services and run them using the docker-compose file.

## Services
### TerminalTransactionVerificationService
Spring server which runs on port 8081 (only the load balancer connects to it).
### WebTransactionVerificationService
Spring server which runs on port 8080 (only the load balancer connects to it).
### UpdaterService
Spring server.

## Load balancers
TerminalTransactionService and WebTransactionService are behind a load balancer.  
The load balancing is done using nginx containers in docker-compose file.  
TerminalTransactionService load balancer listen on port `81`.  
WebTransactionService load balancer listen on port `80`.

## Databases
### Master database
Master database is supposed to be situated in Greenland.  
You can find configurations in its service in the docker-compose file.
### Slave databases
One database is supposed to be situated in France and the other one in Germany.  
You can find configurations in their services in the docker-compose file.  
These databases replicate all the data from the master database when it is updated.

## Stress tests
To execute stress tests, you need to have JMeter installed and launch the scenarios in the `JMeter` directory.