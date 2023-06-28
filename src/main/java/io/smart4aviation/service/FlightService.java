package io.smart4aviation.service;

import io.smart4aviation.model.dto.AirportStatisticDto;

import java.time.OffsetDateTime;

public interface FlightService {

    /**
     * Retrieves the airport statistics for a specific airport on a given date.
     *
     * @param airportIATACode the IATA code of the airport
     * @param date            the date for which to retrieve the statistics
     * @return the airport statistics
     */
    AirportStatisticDto getAirportStatistic(String airportIATACode, OffsetDateTime date);

}