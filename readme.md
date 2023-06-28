# smart4aviation.aero Assignment

## Description


***
I tried to develop the application like a spring boot application. For example;

There are some classes like Spring Jpa interfaces that are under the repository package. However, there are some models representing entities under the model package that are like representing some database tables in spring.

Relations:

FlightEntity 1-m CargoEntity

CargoEntity 1-m FlightBaggage

CargoEntity 1-m FlightCargo

There is an abstract class named AbstractFlightLoad. This class extends by FlightBaggage and FlightCargo. Because these class have the same fields and these fields are process by a util class(like pieces and weight). I preferred to write two entity classes(FlightBaggage and FlightCargo) for that because the requirements are able to change in the future.

The data come from JSON files that are stored in the resources folder. A FileReader util class read and deserialization to Entity classes.

There are two different service interfaces for each functionality. Each service interface has its own implementation (sometimes we can need different implementations, so I use this concept).

There is a Main class acting like a presentation-controller class. It takes some inputs and give outputs on the Java console.  

There are also some unit test to test some critical functionalities. 
***

## How to run

Requirements:

Java 17 and Maven

    The application is using json datas that are stored in the resources folder.
    CargoEntity file is cargoEntity.json and FlightEntity file is flightEntity.json.
    Please chance the files if you want to use different data.
    You can generate diffrent datas with the help of the link below.

* Build

```shell
$ mvn clean install
```

* Run app

```shell
$ java -jar target/smart4aviation-assignment.jar
```

* Note: I had a problem running it from terminal with the standard maven plugin. As a solution, I replaced the plugin with maven-assembly-plugin. Problem solved. Please also try with the compiler if there is any problem. It works fine in the compiler.


## Example

Run:

### Menu:

``` text
Welcome to Smart4Aviation Application
Please choose one of the following options:
-------------------------------------------------------------------------------------------------
1. For requested Flight Number and date will respond with following:
       a. Cargo Weight for requested Flight
       b. Baggage Weight for requested Flight
       c. Total Weight for requested Flight
2. For requested IATA Airport Code and date will respond with following:
       a. Number of flights departing from this airport
       b. Number of flights arriving to this airport
       c. Total number (pieces) of baggage arriving to this airport
       d. Total number (pieces) of baggage departing from this airport
3. Close application
-------------------------------------------------------------------------------------------------
Enter your choice : 

```

### Test Case 1:

*Inputs*

``` text
-------------------------------------------------------------------------------------------------
Enter your choice : 1
-------------------------------------------------------------------------------------------------
Enter Flight Number (format : 1234) : 1191
Enter Date (format : YYYY-MM-ddThh:mm:ss) : 2014-06-22T12:30:11-03:00
-------------------------------------------------------------------------------------------------
```    
*Outputs*
``` text
-------------------------------------------------------------------------------------------------
Flight Cargo Details
Flight Number  : 1191
Date           : 2014-06-22T15:30:11Z
Baggage Weight : 2023.87051894 KG
Cargo Weight   : 1564.6176613500002 KG
Total Weight   : 3588.48818029 KG
-------------------------------------------------------------------------------------------------
```    

### Test Case 2:

*Inputs*

``` text
-------------------------------------------------------------------------------------------------
Enter your choice : 2
-------------------------------------------------------------------------------------------------
Enter IATA Airport Code (format : "MIT","LEW","GDN","KRK","PPX") : YYZ
Enter Date (format : YYYY-MM-ddThh:mm:ss) : 2014-06-22T12:30:11-03:00
-------------------------------------------------------------------------------------------------

```    
*Outputs*
``` text
-------------------------------------------------------------------------------------------------
Airport Statistics
Date                        : 2014-06-22T12:30:11-03:00
Airport IATA Code           : YYZ
Departing Flights           : 1
Departing Baggage Pieces    : 6020
Arriving Flights            : 0
Arriving Baggage Pieces     : 0
-------------------------------------------------------------------------------------------------
``` 

## Generate Test Data

For generating test data use : https://www.json-generator.com/

Flight Entity:
``` text
[
   [
      '{{repeat(5)}}',
   {
      flightId: '{{index()}}',
      flightNumber: '{{integer(1000, 9999)}}',
      departureAirportIATACode: '{{random("SEA","YYZ","YYT","ANC","LAX")}}',
      arrivalAirportIATACode: '{{random("MIT","LEW","GDN","KRK","PPX")}}',
      departureDate: '{{date(new Date(2014, 0, 1), new Date(), "YYYY-MM-ddThh:mm:ssZ")}}'
   }
]
```
Cargo Entity:
``` text
[
   '{{repeat(5)}}',
   {
      flightId: '{{index()}}',
      baggage: [
         '{{repeat(3,8)}}',
      {
         id: '{{index()}}',
         weight: '{{integer(1, 999)}}',
         weightUnit: '{{random("kg","lb")}}',
         pieces: '{{integer(1, 999)}}'
        }
      ],
      cargo: [
         '{{repeat(3,5)}}',
      {
         id: '{{index()}}',
         weight: '{{integer(1, 999)}}',
         weightUnit: '{{random("kg","lb")}}',
         pieces: '{{integer(1, 999)}}'
        }
      ]
   }
]
``` 