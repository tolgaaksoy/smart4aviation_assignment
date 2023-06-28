# smart4aviation.aero Assignment

Your task is to implement application with two functionalities :
1. For requested Flight Number and date will respond with following :
   - Cargo Weight for requested Flight
   - Baggage Weight for requested Flight
   - Total Weight for requested Flight
2. For requested IATA Airport Code and date will respond with following :
   - Number of flights departing from this airport,
   - Number of flights arriving to this airport,
   - Total number (pieces) of baggage arriving to this airport,
   - Total number (pieces) of baggage departing from this airport.

## How to run

Requirements:

Java 17 and Maven

    The application is using json datas that are stored in the resources folder.
    CargoEntity file is cargoEntity.json and FlightEntity file is flightEntity.json.
    Please chance the files if you want to use different data.
    You can generate diffrent datas with the help of the link below.

* Build

```shell
mvn clean install
```

* Run app

```shell
sh java -jar target/assingment-1.0-SNAPSHOT.jar
```

* note: There are some tests included in the project. You can examine the project.


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