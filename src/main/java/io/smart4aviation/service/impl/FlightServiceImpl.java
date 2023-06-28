package io.smart4aviation.service.impl;

import io.smart4aviation.model.dto.AirportStatisticDto;
import io.smart4aviation.model.entity.CargoEntity;
import io.smart4aviation.model.entity.FlightEntity;
import io.smart4aviation.repository.CargoRepository;
import io.smart4aviation.repository.FlightRepository;
import io.smart4aviation.service.FlightService;
import io.smart4aviation.service.util.BaggageUtil;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the FlightService interface.
 */
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final CargoRepository cargoRepository;

    /**
     * Constructs a FlightServiceImpl with default repositories.
     */
    public FlightServiceImpl() {
        this.flightRepository = FlightRepository.getInstance();
        this.cargoRepository = CargoRepository.getInstance();
    }

    /**
     * !!! THIS CONSTRUCTOR IS USED FOR TESTING PURPOSES ONLY !!!
     * Constructs a FlightServiceImpl with the specified repositories.
     *
     * @param flightRepository the flight repository
     * @param cargoRepository  the cargo repository
     */
    public FlightServiceImpl(FlightRepository flightRepository, CargoRepository cargoRepository) {
        this.flightRepository = flightRepository;
        this.cargoRepository = cargoRepository;
    }

    /**
     * Retrieves the airport statistics for the specified airport and date.
     *
     * @param airportIATACode the IATA code of the airport
     * @param date            the date
     * @return the airport statistics
     */
    @Override
    public AirportStatisticDto getAirportStatistic(String airportIATACode, OffsetDateTime date) {
        List<FlightEntity> flightEntityList = flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(airportIATACode, airportIATACode, date);
        if (flightEntityList.isEmpty()) {
            System.out.println("No flights found...");
            return new AirportStatisticDto(airportIATACode, date); // No flights found
        }

        AirportStatisticDto airportStatisticDto = new AirportStatisticDto(airportIATACode, date);

        for (FlightEntity flightEntity : flightEntityList) {
            Optional<CargoEntity> cargoEntityOptional = cargoRepository.getCargoEntityByFlightId(flightEntity.getFlightId());
            if (flightEntity.getDepartureAirportIATACode().equals(airportIATACode)) {
                airportStatisticDto.addDepartingFlights();
                cargoEntityOptional.ifPresent(cargoEntity ->
                        airportStatisticDto.addDepartingBaggagePieces(BaggageUtil.calculatePieces(cargoEntity.getBaggageList())));
                cargoEntityOptional.ifPresent(cargoEntity ->
                        airportStatisticDto.addDepartingBaggagePieces(BaggageUtil.calculatePieces(cargoEntity.getCargoList())));
            }
            if (flightEntity.getArrivalAirportIATACode().equals(airportIATACode)) {
                airportStatisticDto.addArrivingFlights();
                cargoEntityOptional.ifPresent(cargoEntity ->
                        airportStatisticDto.addArrivingBaggagePieces(BaggageUtil.calculatePieces(cargoEntity.getBaggageList())));
                cargoEntityOptional.ifPresent(cargoEntity ->
                        airportStatisticDto.addArrivingBaggagePieces(BaggageUtil.calculatePieces(cargoEntity.getCargoList())));
            }
        }
        return airportStatisticDto;
    }
}