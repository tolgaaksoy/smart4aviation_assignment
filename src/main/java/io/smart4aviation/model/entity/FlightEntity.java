package io.smart4aviation.model.entity;

import java.time.OffsetDateTime;

/**
 * Represents the entity for flights.
 */
public class FlightEntity {
    /**
     * The ID of the flight.
     */
    private Long flightId;
    /**
     * The flight number.
     */
    private int flightNumber;
    /**
     * The IATA code of the departure airport.
     */
    private String departureAirportIATACode;
    /**
     * The IATA code of the arrival airport.
     */
    private String arrivalAirportIATACode;
    /**
     * The departure date of the flight.
     */
    private OffsetDateTime departureDate;

    /**
     * Constructs an empty FlightEntity object.
     */
    public FlightEntity() {
    }

    /**
     * Constructs a FlightEntity object with the specified flight ID, flight number,
     * departure airport IATA code, arrival airport IATA code, and departure date.
     *
     * @param flightId                  the ID of the flight
     * @param flightNumber              the flight number
     * @param departureAirportIATACode  the IATA code of the departure airport
     * @param arrivalAirportIATACode    the IATA code of the arrival airport
     * @param departureDate             the departure date of the flight
     */
    public FlightEntity(Long flightId, int flightNumber, String departureAirportIATACode, String arrivalAirportIATACode, OffsetDateTime departureDate) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirportIATACode = departureAirportIATACode;
        this.arrivalAirportIATACode = arrivalAirportIATACode;
        this.departureDate = departureDate;
    }

    /**
     * Returns the ID of the flight.
     *
     * @return the ID of the flight
     */
    public Long getFlightId() {
        return flightId;
    }

    /**
     * Sets the ID of the flight.
     *
     * @param flightId the ID to set for the flight
     */
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    /**
     * Returns the flight number.
     *
     * @return the flight number
     */
    public int getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the flight number.
     *
     * @param flightNumber the flight number to set
     */
    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Returns the IATA code of the departure airport.
     *
     * @return the IATA code of the departure airport
     */
    public String getDepartureAirportIATACode() {
        return departureAirportIATACode;
    }

    /**
     * Sets the IATA code of the departure airport.
     *
     * @param departureAirportIATACode the IATA code to set for the departure airport
     */
    public void setDepartureAirportIATACode(String departureAirportIATACode) {
        this.departureAirportIATACode = departureAirportIATACode;
    }

    /**
     * Returns the IATA code of the arrival airport.
     *
     * @return the IATA code of the arrival airport
     */
    public String getArrivalAirportIATACode() {
        return arrivalAirportIATACode;
    }

    /**
     * Sets the IATA code of the arrival airport.
     *
     * @param arrivalAirportIATACode the IATA code to set for the arrival airport
     */
    public void setArrivalAirportIATACode(String arrivalAirportIATACode) {
        this.arrivalAirportIATACode = arrivalAirportIATACode;
    }

    /**
     * Returns the departure date of the flight.
     *
     * @return the departure date of the flight
     */
    public OffsetDateTime getDepartureDate() {
        return departureDate;
    }

    /**
     * Sets the departure date of the flight.
     *
     * @param departureDate the departure date to set for the flight
     */
    public void setDepartureDate(OffsetDateTime departureDate) {
        this.departureDate = departureDate;
    }
}
