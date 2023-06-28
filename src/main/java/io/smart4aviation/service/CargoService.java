package io.smart4aviation.service;

import io.smart4aviation.model.dto.FlightCargoDetailDto;
import io.smart4aviation.model.enums.WeightUnit;

import java.time.OffsetDateTime;


public interface CargoService {

    /**
     * Retrieves the cargo details for a flight identified by the flight number and date,
     * using the default weight unit.
     *
     * @param flightNumber the flight number
     * @param date         the date of the flight
     * @return the cargo details for the flight
     */
    FlightCargoDetailDto getFlightCargoDetail(int flightNumber, OffsetDateTime date);

    /**
     * Retrieves the cargo details for a flight identified by the flight number and date,
     * using the specified weight unit.
     *
     * @param flightNumber the flight number
     * @param date         the date of the flight
     * @param weightUnit   the weight unit to use for the cargo details
     * @return the cargo details for the flight
     */
    FlightCargoDetailDto getFlightCargoDetail(int flightNumber, OffsetDateTime date, WeightUnit weightUnit);

}
