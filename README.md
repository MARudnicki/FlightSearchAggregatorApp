
# Application Description
Create an API to search over available flights. 
There are two available flight’s providers:

- https://obscure-caverns-79008.herokuapp.com/cheap
- https://obscure-caverns-79008.herokuapp.com/business

API should aggregate return flights from both providers. Do remember about sorting, filtering and pagination.


Note  - To install all dependencies, compile and run tests - Please follow steps mentioned below
to install java & maven.


#Manual sandbox setup steps

#Pre-requisite:
=================================
Install mvn version 3.9.0 or higher
Install jdk version 1.8.*
---------------------------------

Set classpath for java and maven
=================================
```
e.g.
export M2_HOME=/Users/mmundra/Dev/install/buildtools/apache-maven-3.3.9/
export M2=$M2_HOME/bin
export PATH=$M2:$PATH
```
---------------------------------


Build & Run  application on local sandbox:
=================================
Build command - ```mvn clean install```

---------------------------------


Running application on local:
=================================

Run application on CLI - ```mvn spring-boot:run```

---------------------------------

Shell scripts to run test suit & application :
=================================

Project's bin directory consist of 2 shell scripts:

1 - start_application.sh

    This cleans compiles and starts the boot application

2 - run_functional_tests.sh

    This only does clean compile and executes all the test cases

Note - For making file executable use below command:

```sudo chmod +x <FILE_NAME>```

-----------------------------------

#Sequence Diagram

https://cdn.pbrd.co/images/HWLZcSW.png


#Tech Debt - Remaining things to do list
- Moving all the direct literal check to enum
- making backend changes to segregate search criteria parameter and filter
  as of now when we give some value in criteria, then response is fetched with flights having those as 
  part of flight
  E.g. Category is set to "Cheap" in search criteria
  then all the flights will be returned, which are having flight class "Cheap" and so on
- test coverage can be increased
- filter & sort by date is not working correctly and can be taken as a next enhancement 


# NOTE - BY DEFAULT PAGE SIZE IS 2 & IT RETURNS FIRST PAGE ONLY


Here are the sample local sandbox link for seeing the response

http://localhost:8080/flights/api/search?pageSize=20

http://localhost:8080/flights/api/search?pageSize=20&sortBy=Category
  
  