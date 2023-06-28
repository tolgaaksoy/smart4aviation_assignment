package io.smart4aviation.service.impl;

import io.smart4aviation.model.dto.AirportStatisticDto;
import io.smart4aviation.model.entity.CargoEntity;
import io.smart4aviation.model.entity.FlightBaggage;
import io.smart4aviation.model.entity.FlightCargo;
import io.smart4aviation.model.entity.FlightEntity;
import io.smart4aviation.model.enums.WeightUnit;
import io.smart4aviation.repository.CargoRepository;
import io.smart4aviation.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class FlightServiceImplTest {
    @Mock
    FlightRepository flightRepository;
    @Mock
    CargoRepository cargoRepository;
    @InjectMocks
    FlightServiceImpl flightServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAirportStatistic_WhenNoCargoFound_ContinuesToNextFlight() {
        // Arrange
        String departureAirportIATACode = "ABC";
        String arrivalAirportIATACode = "DEF";
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity1 = new FlightEntity(1L, 1000, departureAirportIATACode, arrivalAirportIATACode, date);
        FlightEntity flightEntity2 = new FlightEntity(2L, 1001, departureAirportIATACode, arrivalAirportIATACode, date);
        List<FlightEntity> flightEntityList = Arrays.asList(flightEntity1, flightEntity2);

        when(flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(departureAirportIATACode, departureAirportIATACode, date))
                .thenReturn(flightEntityList);

        // Act
        AirportStatisticDto result = flightServiceImpl.getAirportStatistic(departureAirportIATACode, date);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getDepartingFlights());
        assertEquals(0, result.getDepartingBaggagePieces());
        assertEquals(0, result.getArrivingFlights());
    }

    @Test
    void testGetAirportStatistic_WithDepartingFlights_ReturnsAirportStatisticDto() {
        // Arrange
        String departureAirportIATACode = "ABC";
        String arrivalAirportIATACode = "DEF";
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity1 = new FlightEntity(1L, 1, departureAirportIATACode, arrivalAirportIATACode, date);
        FlightEntity flightEntity2 = new FlightEntity(2L, 1, arrivalAirportIATACode, departureAirportIATACode, date);
        List<FlightEntity> flightEntityList = Arrays.asList(flightEntity1, flightEntity2);

        CargoEntity cargoEntity1 = new CargoEntity(1L, null, null);
        CargoEntity cargoEntity2 = new CargoEntity(2L, null, null);

        when(flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(departureAirportIATACode, departureAirportIATACode, date))
                .thenReturn(flightEntityList);
        when(cargoRepository.getCargoEntityByFlightId(1L)).thenReturn(Optional.of(cargoEntity1));
        when(cargoRepository.getCargoEntityByFlightId(2L)).thenReturn(Optional.of(cargoEntity2));

        // Act
        AirportStatisticDto result = flightServiceImpl.getAirportStatistic(departureAirportIATACode, date);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getDepartingFlights());
        assertEquals(1, result.getArrivingFlights());
    }

    @Test
    void testGetAirportStatistic_WithArrivingFlights_ReturnsAirportStatisticDto() {
        // Arrange
        String departureAirportIATACode = "ABC";
        String arrivalAirportIATACode = "DEF";
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity1 = new FlightEntity(1L, 1000, departureAirportIATACode, arrivalAirportIATACode, date);
        FlightEntity flightEntity2 = new FlightEntity(2L, 1001, departureAirportIATACode, arrivalAirportIATACode, date);

        CargoEntity cargoEntity1 = new CargoEntity(1L, null, null);
        CargoEntity cargoEntity2 = new CargoEntity(2L, null, null);

        when(flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(arrivalAirportIATACode, arrivalAirportIATACode, date))
                .thenReturn(Arrays.asList(flightEntity1, flightEntity2));
        when(cargoRepository.getCargoEntityByFlightId(1L)).thenReturn(Optional.of(cargoEntity1));
        when(cargoRepository.getCargoEntityByFlightId(2L)).thenReturn(Optional.of(cargoEntity2));

        // Act
        AirportStatisticDto result = flightServiceImpl.getAirportStatistic(arrivalAirportIATACode, date);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getArrivingFlights());
    }

    @Test
    void testGetAirportStatistic_WithNoCargo_ReturnsNull() {
        // Arrange
        String departureAirportIATACode = "ABC";
        String arrivalAirportIATACode = "DEF";
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity1 = new FlightEntity(1L, 1000, departureAirportIATACode, arrivalAirportIATACode, date);
        FlightEntity flightEntity2 = new FlightEntity(2L, 1001, departureAirportIATACode, arrivalAirportIATACode, date);

        when(flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(departureAirportIATACode, departureAirportIATACode, date))
                .thenReturn(Arrays.asList(flightEntity1, flightEntity2));

        when(cargoRepository.getCargoEntityByFlightId(1L)).thenReturn(Optional.empty());
        when(cargoRepository.getCargoEntityByFlightId(2L)).thenReturn(Optional.empty());

        // Act
        AirportStatisticDto result = flightServiceImpl.getAirportStatistic(departureAirportIATACode, date);

        // Assert
        assertEquals(0, result.getArrivingBaggagePieces());
        assertEquals(2, result.getDepartingFlights());
    }

    @Test
    void testGetAirportStatistic_WithDepartingAndArrivingFlights_ReturnsAirportStatisticDto() {
        // Arrange
        String departureAirportIATACode = "ABC";
        String arrivalAirportIATACode = "DEF";
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity1 = new FlightEntity(1L, 1000, departureAirportIATACode, arrivalAirportIATACode, date);
        FlightEntity flightEntity2 = new FlightEntity(2L, 1001, arrivalAirportIATACode, departureAirportIATACode, date);

        CargoEntity cargoEntity1 = new CargoEntity(1L, null, null);
        CargoEntity cargoEntity2 = new CargoEntity(2L, null, null);

        when(flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(departureAirportIATACode, departureAirportIATACode, date))
                .thenReturn(Arrays.asList(flightEntity1, flightEntity2));
        when(cargoRepository.getCargoEntityByFlightId(1L)).thenReturn(Optional.of(cargoEntity1));
        when(cargoRepository.getCargoEntityByFlightId(2L)).thenReturn(Optional.of(cargoEntity2));

        // Act
        AirportStatisticDto result = flightServiceImpl.getAirportStatistic(departureAirportIATACode, date);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getDepartingFlights());
        assertEquals(1, result.getArrivingFlights());
    }

    @Test
    void testGetAirportStatistic_WithMultipleFlightsAndCargo_ReturnsAirportStatisticDto() {
        // Arrange
        String airportIATACode = "ABC";
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity1 = new FlightEntity(1L, 1000, airportIATACode, "DEF", date);
        FlightEntity flightEntity2 = new FlightEntity(2L, 1001, "GHI", airportIATACode, date);
        FlightEntity flightEntity3 = new FlightEntity(3L, 1002, airportIATACode, "JKL", date);

        CargoEntity cargoEntity1 = new CargoEntity(1L, null, null);
        CargoEntity cargoEntity2 = new CargoEntity(2L, null, null);
        CargoEntity cargoEntity3 = new CargoEntity(3L, null, null);

        when(flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(airportIATACode, airportIATACode, date))
                .thenReturn(Arrays.asList(flightEntity1, flightEntity2, flightEntity3));
        when(cargoRepository.getCargoEntityByFlightId(1L)).thenReturn(Optional.of(cargoEntity1));
        when(cargoRepository.getCargoEntityByFlightId(2L)).thenReturn(Optional.of(cargoEntity2));
        when(cargoRepository.getCargoEntityByFlightId(3L)).thenReturn(Optional.of(cargoEntity3));

        // Act
        AirportStatisticDto result = flightServiceImpl.getAirportStatistic(airportIATACode, date);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getDepartingFlights());
        assertEquals(1, result.getArrivingFlights());
    }

    @Test
    void testGetAirportStatistic_WithNoFlights_ReturnsAirportStatisticDtoWithZeroCounts() {
        // Arrange
        String airportIATACode = "ABC";
        OffsetDateTime date = OffsetDateTime.now();

        when(flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(airportIATACode, airportIATACode, date))
                .thenReturn(Collections.emptyList());

        // Act
        AirportStatisticDto result = flightServiceImpl.getAirportStatistic(airportIATACode, date);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getDepartingFlights());
        assertEquals(0, result.getArrivingFlights());
    }

    @Test
    void testGetAirportStatistic_WithCargoPieces_ReturnsAirportStatisticDtoWithCargoPiecesCount() {
        // Arrange
        String departureAirportIATACode = "ABC";
        String arrivalAirportIATACode = "DEF";
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity departingFlightEntity = new FlightEntity(1L, 1000, departureAirportIATACode, arrivalAirportIATACode, date);

        FlightBaggage flightBaggage1 = new FlightBaggage(1L, 10.0, WeightUnit.KG, 2);
        FlightBaggage flightBaggage2 = new FlightBaggage(2L, 20.0, WeightUnit.KG, 1);
        FlightBaggage flightBaggage3 = new FlightBaggage(3L, 30.0, WeightUnit.KG, 1);

        CargoEntity departingCargoEntity = new CargoEntity(1L, null, Arrays.asList(flightBaggage1, flightBaggage2, flightBaggage3));


        FlightEntity arrivingFlightEntity = new FlightEntity(2L, 1001, arrivalAirportIATACode, departureAirportIATACode, date);

        FlightCargo flightCargo1 = new FlightCargo(1L, 10.0, WeightUnit.KG, 2);
        FlightCargo flightCargo2 = new FlightCargo(2L, 20.0, WeightUnit.KG, 3);
        FlightCargo flightCargo3 = new FlightCargo(3L, 30.0, WeightUnit.KG, 1);

        CargoEntity arrivingCargoEntity = new CargoEntity(2L, Arrays.asList(flightCargo1, flightCargo2, flightCargo3), null);

        when(flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(departureAirportIATACode, departureAirportIATACode, date))
                .thenReturn(Arrays.asList(departingFlightEntity, arrivingFlightEntity));
        when(cargoRepository.getCargoEntityByFlightId(1L)).thenReturn(Optional.of(departingCargoEntity));
        when(cargoRepository.getCargoEntityByFlightId(2L)).thenReturn(Optional.of(arrivingCargoEntity));

        // Act
        AirportStatisticDto result = flightServiceImpl.getAirportStatistic(departureAirportIATACode, date);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getDepartingFlights());
        assertEquals(4, result.getDepartingBaggagePieces());

        assertEquals(1, result.getArrivingFlights());
        assertEquals(6, result.getArrivingBaggagePieces());
    }

}