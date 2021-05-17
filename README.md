# Getting Started

This project is a RestFul API to find prime numbers for a given limit.

# Key Features :
 - Self Explanatory code, easy to maintain and share [including logging + further code documentation] 
 - in memory H2 database for testing 
 - Caching for faster performance 
 - Swagger RestAPI documentation and testing user interface
 - Aspect Oriented programming with custom annotation for better code readability and validations 

# TechStack List :

 - SpringBoot
 - Spring Test
 - Mockito/jUnit for Testing
 - JpaRepository
 - H2 Database [in Memory]
 - H2 Database UI
 - Swagger-2 for Api Documentation and testing locally

# Running Locally :

 - Simply run the Application main method or use mvn clean install and mvn spring-boot:run commands to start the project
 - Once its up and running please navigate to http://localhost:8080/swagger-ui.html page
 - Click on PrimeNumberController and you will find get api request details [/primes/{limit}]
 - Click on try-it-out button and enter upper limit value [example : 100]
 - Please note you can select response/media type from the dropdown application/json or application/xml  
 - If you are testing with other tools such as curl or postman , please add 'accept' : 'application/json' or 'application/xml' header  
 - Click execute and you will get back the list of prime numbers for a given number.

# Connecting to H2 in-memory database console in browser

 - Navigate to http://localhost:8080/h2-console in the browser
 - On the Login screen change JDBC URL to jdbc:h2:mem:testdb
 - leave the other details as it is [username:sa and password is blank]  
 - click on connect
 - Once connected you can run any SQL inside the console to verify data [sample SQL query below]
 - select p.id ,pc.primes from prime_number p, prime_number_primes pc;

# Running Unit tests

 - Run mvn clean install
 - This will build the project and run all the unit tests

# Java Code Coverage - Unit Tests 

 - This API has been tested using fully integrated the unit test framework
 - when you run mvn clean install, it will run the test and generate a code coverage report in target folder
 - Navigate to /target/site/jacoco/index.html in the browser to check the 100% code overage

