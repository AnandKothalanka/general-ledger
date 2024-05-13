# Project description
This is a ledger posting system. 
The project follows a CQRS pattern. command-ledger takes the commands to create accounts and transactions through rest api
and creates them in its db and posts a message in kafka so that the query-ledger app reads it and updates its database.

This is a multi-module maven project where command-ledger and query-ledger have their own databases and are independent and can be scaled individually.

# Tech used

java 17, Spring boot, Axon framework, Kafka, CQRS command pattern, mysql, junit
# How to build 

* Build the project by going to the parent pom and doing mvn clean install
* Run the docker-compose file in docker folder (contains 2 instances of mysql, axon server, kafka)
* Run the Application files as spring boot apps in command-ledger and query-ledger modules


# Getting Started

to create account - use the api in AccountController of project command-ledger
to create transaction - use the api in TransactionController of project command-ledger 

to view the trialbalance - use the api in TrailBalanceController of project query-ledger
