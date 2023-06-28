package io.smart4aviation.service.impl;

import io.smart4aviation.model.dto.FlightCargoDetailDto;
import io.smart4aviation.model.entity.CargoEntity;
import io.smart4aviation.model.entity.FlightEntity;
import io.smart4aviation.model.enums.WeightUnit;
import io.smart4aviation.repository.CargoRepository;
import io.smart4aviation.repository.FlightRepository;
import io.smart4aviation.service.CargoService;
import io.smart4aviation.service.util.BaggageUtil;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * Implementation of the CargoService interface.
 */
public class CargoServiceImpl implements CargoService {

    private static final WeightUnit DEFAULT_WEIGHT_UNIT = WeightUnit.KG;

    private final FlightRepository flightRepository;
    private final CargoRepository cargoRepository;

    /**
     * Constructs a CargoServiceImpl with default repositories.
     */
    public CargoServiceImpl() {
        this.flightRepository = FlightRepository.getInstance();
        this.cargoRepository = CargoRepository.getInstance();
    }

    /**
     * !!! THIS CONSTRUCTOR IS USED FOR TESTING PURPOSES ONLY !!!
     * Constructs a CargoServiceImpl with the specified repositories.
     *
     * @param flightRepository the flight repository
     * @param cargoRepository  the cargo repository
     */
    public CargoServiceImpl(FlightRepository flightRepository, CargoRepository cargoRepository) {
        this.flightRepository = flightRepository;
        this.cargoRepository = cargoRepository;
    }

    @Override
    public FlightCargoDetailDto getFlightCargoDetail(int flightNumber, OffsetDateTime date) {
        return getFlightCargoDetail(flightNumber, date, DEFAULT_WEIGHT_UNIT);
    }

    @Override
    public FlightCargoDetailDto getFlightCargoDetail(int flightNumber, OffsetDateTime date, WeightUnit weightUnit) {
        Optional<FlightEntity> optionalFlightEntity = flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date);
        if (optionalFlightEntity.isEmpty()) {
            System.out.println("Flight not found...");
            return null; // Flight not found
        }
        FlightEntity flightEntity = optionalFlightEntity.get();
        FlightCargoDetailDto flightCargoDetailDto = new FlightCargoDetailDto(flightEntity.getFlightNumber(), flightEntity.getDepartureDate(), weightUnit);

        Optional<CargoEntity> optionalCargoEntity = cargoRepository.getCargoEntityByFlightId(flightEntity.getFlightId());
        optionalCargoEntity.ifPresent(cargoEntity ->
                flightCargoDetailDto.setBaggageWeight(BaggageUtil.calculateWeight(cargoEntity.getBaggageList(), weightUnit)));
        optionalCargoEntity.ifPresent(cargoEntity ->
                flightCargoDetailDto.setCargoWeight(BaggageUtil.calculateWeight(cargoEntity.getCargoList(), weightUnit)));
        flightCargoDetailDto.calculateTotalWeight();

        return flightCargoDetailDto;
    }
}