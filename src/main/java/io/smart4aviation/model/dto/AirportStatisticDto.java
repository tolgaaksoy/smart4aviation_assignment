package io.smart4aviation.model.dto;

import java.time.OffsetDateTime;

/**
 * Represents the data transfer object for airport statistics.
 */
public class AirportStatisticDto {

    /**
     * The IATA code of the airport.
     */
    private final String airportIATACode;

    /**
     * The date of the airport statistics.
     */
    private final OffsetDateTime date;

    /**
     * The number of departing flights.
     */
    private int departingFlights = 0;

    /**
     * The number of arriving flights.
     */
    private int arrivingFlights = 0;

    /**
     * The number of departing baggage pieces.
     */
    private int departingBaggagePieces = 0;

    /**
     * The number of arriving baggage pieces.
     */
    private int arrivingBaggagePieces = 0;

    /**
     * Constructs an AirportStatisticDto object with the specified airport IATA code and date.
     *
     * @param airportIATACode The IATA code of the airport.
     * @param date The date of the airport statistics.
     */
    public AirportStatisticDto(String airportIATACode, OffsetDateTime date) {
        this.airportIATACode = airportIATACode;
        this.date = date;
    }

    /**
     * Returns the date of the airport statistics.
     *
     * @return The date of the airport statistics.
     */
    public OffsetDateTime getDate() {
        return date;
    }

    /**
     * Returns the number of departing flights.
     *
     * @return The number of departing flights.
     */
    public int getDepartingFlights() {
        return departingFlights;
    }

    /**
     * Sets the number of departing flights.
     *
     * @param departingFlights The number of departing flights.
     */
    public void setDepartingFlights(int departingFlights) {
        this.departingFlights = departingFlights;
    }

    /**
     * Returns the number of arriving flights.
     *
     * @return The number of arriving flights.
     */
    public int getArrivingFlights() {
        return arrivingFlights;
    }

    /**
     * Sets the number of arriving flights.
     *
     * @param arrivingFlights The number of arriving flights.
     */
    public void setArrivingFlights(int arrivingFlights) {
        this.arrivingFlights = arrivingFlights;
    }

    /**
     * Returns the number of departing baggage pieces.
     *
     * @return The number of departing baggage pieces.
     */
    public int getDepartingBaggagePieces() {
        return departingBaggagePieces;
    }

    /**
     * Sets the number of departing baggage pieces.
     *
     * @param departingBaggagePieces The number of departing baggage pieces.
     */
    public void setDepartingBaggagePieces(int departingBaggagePieces) {
        this.departingBaggagePieces = departingBaggagePieces;
    }

    /**
     * Returns the number of arriving baggage pieces.
     *
     * @return The number of arriving baggage pieces.
     */
    public int getArrivingBaggagePieces() {
        return arrivingBaggagePieces;
    }

    /**
     * Sets the number of arriving baggage pieces.
     *
     * @param arrivingBaggagePieces The number of arriving baggage pieces.
     */
    public void setArrivingBaggagePieces(int arrivingBaggagePieces) {
        this.arrivingBaggagePieces = arrivingBaggagePieces;
    }

    /**
     * Increments the number of departing flights by 1.
     */
    public void addDepartingFlights() {
        this.departingFlights++;
    }

    /**
     * Increments the number of departing flights by the specified value.
     *
     * @param departingFlights The value to increment the number of departing flights by.
     */
    public void addDepartingFlights(int departingFlights) {
        this.departingFlights += departingFlights;
    }

    /**
     * Increments the number of arriving flights by 1.
     */
    public void addArrivingFlights() {
        this.arrivingFlights++;
    }

    /**
     * Increments the number of arriving flights by the specified value.
     *
     * @param arrivingFlights The value to increment the number of arriving flights by.
     */
    public void addArrivingFlights(int arrivingFlights) {
        this.arrivingFlights += arrivingFlights;
    }

    /**
     * Increments the number of departing baggage pieces by 1.
     */
    public void addDepartingBaggagePieces() {
        this.departingBaggagePieces++;
    }

    /**
     * Increments the number of departing baggage pieces by the specified value.
     *
     * @param departingBaggagePieces The value to increment the number of departing baggage pieces by.
     */
    public void addDepartingBaggagePieces(int departingBaggagePieces) {
        this.departingBaggagePieces += departingBaggagePieces;
    }

    /**
     * Increments the number of arriving baggage pieces by 1.
     */
    public void addArrivingBaggagePieces() {
        this.arrivingBaggagePieces++;
    }

    /**
     * Increments the number of arriving baggage pieces by the specified value.
     *
     * @param arrivingBaggagePieces The value to increment the number of arriving baggage pieces by.
     */
    public void addArrivingBaggagePieces(int arrivingBaggagePieces) {
        this.arrivingBaggagePieces += arrivingBaggagePieces;
    }

    /**
     * Returns a string representation of the AirportStatisticDto object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Airport Statistics\n" +
                "Date                        : " + date + "\n" +
                "Airport IATA Code           : " + airportIATACode + "\n" +
                "Departing Flights           : " + departingFlights + "\n" +
                "Departing Baggage Pieces    : " + departingBaggagePieces + "\n" +
                "Arriving Flights            : " + arrivingFlights + "\n" +
                "Arriving Baggage Pieces     : " + arrivingBaggagePieces;
    }
}