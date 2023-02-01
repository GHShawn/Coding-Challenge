# Get Started

## Prerequisite (Mac version)

   1. Check if the java version is 17.0.1
      * application gradle version set to 7.3 so it support JAVA 17.0
      * if JAVA version is lower than 17.0 - downgrade the gradle version by change to gradle.properties file
      * replace the url (https\://services.gradle.org/distributions/gradle-6.3-bin.zip)
   2. Install IDE (VS Code/IntelliJ)
   3. Install Postman
  

## Steps:

   1. Clone Repository 
      ```git clone httplink```
   2. Open the project folder in IDE (VS Code/IntelliJ)
   3. Run command to start the application
      ```./gradlew bootRun```
      Add permission if require
      ```chmod 777 ./gradlew```
      
## API Endpoints

### Task1 Get Total Number Of Reports By Employee ID
```
http://localhost:8080/reporting/16a596ae-edd3-4847-99fe-c4518e82c86f
```

### Task2 Compensation Create and Read
1. POST

```
http://localhost:8080/compensation
JSON Object

   {   
        "employee":{
           "firstName": "John",
           "lastName": "Smith",
           "position": "SE",
           "department": "Software Enginner",
           "directReports": []
       },
       "salary": 900000,
       "effectiveDate": "2020-02-05"
    }
```

2. GET
```
http://localhost:8080/compensation/{id}
```

## UNIT TEST
Should be able to run the unit tests from the IDE


# Coding Challenge
## What's Provided
A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped 
with data. The application contains information about all employees at a company. On application start-up, an in-memory 
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

### How to Run
The application may be executed by running `gradlew bootRun`.

### How to Use
The following endpoints are available to use:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```
The Employee has a JSON schema of:
```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```
For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement
Clone or download the repository, do not fork it.

### Task 1
Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of 
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example, 
given the following employee structure:
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4. 

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return 
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will 
not be persisted.

### Task 2
Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create 
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the 
Compensation from the persistence layer.

## Delivery
Please upload your results to a publicly accessible Git repo. Free ones are provided by Github and Bitbucket.
