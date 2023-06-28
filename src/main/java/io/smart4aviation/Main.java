package io.smart4aviation;

import io.smart4aviation.service.CargoService;
import io.smart4aviation.service.FlightService;
import io.smart4aviation.service.impl.CargoServiceImpl;
import io.smart4aviation.service.impl.FlightServiceImpl;

import java.time.OffsetDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final String LINE = "-------------------------------------------------------------------------------------------------";

    private Main() {
        throw new IllegalStateException("Utility class");
    }

    public static void main(String[] args) {

        CargoService cargoService = new CargoServiceImpl();
        FlightService flightService = new FlightServiceImpl();

        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                -------------------------------------------------------------------------------------------------
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
                -------------------------------------------------------------------------------------------------""");

        while (true) {
            System.out.print("Enter your choice : ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Choice.  Please choose from 1, 2 or 3");
                System.out.println(LINE);
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println(LINE);
                    System.out.print("Enter Flight Number (format : 1234) : ");
                    int flightNumber = scanner.nextInt();
                    if (flightNumber < 0 || flightNumber > 9999) {
                        System.out.println("Invalid Flight Number. Flight Number should be in format : 1234");
                        System.out.println(LINE);
                        break;
                    }
                    System.out.print("Enter Date (format : YYYY-MM-ddThh:mm:ss) : ");
                    String date = scanner.next();
                    System.out.println(LINE);
                    try {
                        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
                        System.out.println(cargoService.getFlightCargoDetail(flightNumber, offsetDateTime));
                        System.out.println(LINE);
                    } catch (Exception e) {
                        System.out.println("Invalid Date. Date should be in format : YYYY-MM-ddThh:mm:ss");
                        System.out.println(LINE);
                    }
                }
                case 2 -> {
                    System.out.println(LINE);
                    System.out.print("Enter IATA Airport Code (format : \"MIT\",\"LEW\",\"GDN\",\"KRK\",\"PPX\") : ");
                    String airportIATACode = scanner.next();
                    if (airportIATACode.length() != 3 || !airportIATACode.matches("[A-Z]+")) {
                        System.out.println("Invalid IATA Airport Code. IATA Airport Code should be in format : \"MIT\",\"LEW\",\"GDN\",\"KRK\",\"PPX\"");
                        System.out.println(LINE);
                        break;
                    }
                    System.out.print("Enter Date (format : YYYY-MM-ddThh:mm:ss) : ");
                    String date1 = scanner.next();
                    System.out.println(LINE);
                    try {
                        OffsetDateTime offsetDateTime1 = OffsetDateTime.parse(date1);
                        System.out.println(flightService.getAirportStatistic(airportIATACode, offsetDateTime1));
                        System.out.println(LINE);
                    } catch (Exception e) {
                        System.out.println("Invalid Date. Date should be in format : YYYY-MM-ddThh:mm:ss");
                        System.out.println(LINE);
                    }
                }
                case 3 -> {
                    System.out.println("Closing Application");
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid Choice. Please choose from 1, 2 or 3");
                    System.out.println(LINE);
                }
            }
        }
    }
}