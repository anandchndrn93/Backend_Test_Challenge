# Backend_Test_Challenge

## Technology Stack
1. java 11.0.11
2. Apache Maven 3.8.1
3. TestNG 7.0.0
Logging using Apache Log4j.

## CI Integration
CI integrations is done using CircleCI and has added github checkes. Every time a pull request is raised or code is merged to master the tests will be triggered

## How to Run
The test class is **/src/main/java/tests/Tests.java**. A _testng.xml_ file is created in project path. This file can be executed using maven by simply running command **mvn test** 
1. open command prompt
2. navigate to project
3. run mvn test

java and maven must be installed and added to PATH
