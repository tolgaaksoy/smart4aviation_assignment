package io.smart4aviation.service.impl;

import io.smart4aviation.model.dto.FlightCargoDetailDto;
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
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CargoServiceImplTest {
    @Mock
    FlightRepository flightRepository;
    @Mock
    CargoRepository cargoRepository;
    @InjectMocks
    CargoServiceImpl cargoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFlightCargoDetail_WithValidFlightNumberAndDate_ReturnsFlightCargoDetailDto() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(1L);
        flightEntity.setFlightNumber(flightNumber);
        flightEntity.setDepartureDate(date);

        CargoEntity cargoEntity = new CargoEntity();

        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date))
                .thenReturn(Optional.of(flightEntity));
        when(cargoRepository.getCargoEntityByFlightId(anyLong()))
                .thenReturn(Optional.of(cargoEntity));

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertEquals(flightNumber, result.getFlightNumber());
        assertEquals(date, result.getDate());
    }

    @Test
    void testGetFlightCargoDetail_WithInvalidFlightNumberAndDate_ReturnsNull() {
        // Arrange
        int flightNumber = 123;
        OffsetDateTime date = OffsetDateTime.now();

        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date))
                .thenReturn(Optional.empty());

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertEquals(null, result);
        verify(cargoRepository, never()).getCargoEntityByFlightId(anyLong());
    }

    @Test
    void testGetFlightCargoDetail_WithInvalidCargo_ReturnsFlightCargoDetailDtoWithZeroWeights() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(1L);
        flightEntity.setFlightNumber(flightNumber);
        flightEntity.setDepartureDate(date);

        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date))
                .thenReturn(Optional.of(flightEntity));
        when(cargoRepository.getCargoEntityByFlightId(anyLong()))
                .thenReturn(Optional.empty());

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertEquals(flightNumber, result.getFlightNumber());
        assertEquals(date, result.getDate());
        assertEquals(0.0, result.getBaggageWeight());
        assertEquals(0.0, result.getCargoWeight());
        assertEquals(0.0, result.getTotalWeight());
    }

    @Test
    void testGetFlightCargoDetail_calculateBaggageWeight() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(1L);
        flightEntity.setFlightNumber(flightNumber);
        flightEntity.setDepartureDate(date);

        CargoEntity cargoEntity = new CargoEntity();
        FlightBaggage baggage = new FlightBaggage();
        baggage.setWeight(50.0);
        baggage.setWeightUnit(WeightUnit.KG);
        cargoEntity.setBaggageList(Collections.singletonList(baggage));

        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date)).thenReturn(Optional.of(flightEntity));
        when(cargoRepository.getCargoEntityByFlightId(anyLong())).thenReturn(Optional.of(cargoEntity));

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertNotNull(result);
        assertEquals(50.0, result.getBaggageWeight());
        verify(flightRepository).getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date);
        verify(cargoRepository).getCargoEntityByFlightId(anyLong());
    }


    @Test
    void testGetFlightCargoDetail_calculateCargoWeight() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(1L);
        flightEntity.setFlightNumber(flightNumber);
        flightEntity.setDepartureDate(date);

        CargoEntity cargoEntity = new CargoEntity();
        FlightCargo cargo = new FlightCargo();
        cargo.setWeight(100.0);
        cargo.setWeightUnit(WeightUnit.KG);
        cargoEntity.setCargoList(Collections.singletonList(cargo));
        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date)).thenReturn(Optional.of(flightEntity));
        when(cargoRepository.getCargoEntityByFlightId(anyLong())).thenReturn(Optional.of(cargoEntity));

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertNotNull(result);
        assertEquals(100.0, result.getCargoWeight());
        verify(flightRepository).getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date);
        verify(cargoRepository).getCargoEntityByFlightId(anyLong());
    }


    @Test
    void testGetFlightCargoDetail_calculateTotalWeight() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(1L);
        flightEntity.setFlightNumber(flightNumber);
        flightEntity.setDepartureDate(date);

        CargoEntity cargoEntity = new CargoEntity();
        FlightBaggage baggage = new FlightBaggage();
        baggage.setWeight(50.0);
        baggage.setWeightUnit(WeightUnit.KG);
        cargoEntity.setBaggageList(Collections.singletonList(baggage));
        FlightCargo cargo = new FlightCargo();
        cargo.setWeight(100.0);
        cargo.setWeightUnit(WeightUnit.KG);
        cargoEntity.setCargoList(Collections.singletonList(cargo));
        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date)).thenReturn(Optional.of(flightEntity));
        when(cargoRepository.getCargoEntityByFlightId(anyLong())).thenReturn(Optional.of(cargoEntity));

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertNotNull(result);
        assertEquals(150.0, result.getTotalWeight());
        verify(flightRepository).getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date);
        verify(cargoRepository).getCargoEntityByFlightId(anyLong());
    }

    @Test
    void testGetFlightCargoDetail_nullDepartureDate() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = null;

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertNull(result);
        verify(flightRepository, never()).getFlightEntityByFlightNumberAndDepartureDate(anyInt(), any(OffsetDateTime.class));
        verify(cargoRepository, never()).getCargoEntityByFlightId(anyLong());
    }

    @Test
    void testGetFlightCargoDetail_emptyCargo() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = OffsetDateTime.now();

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(1L);
        flightEntity.setFlightNumber(flightNumber);
        flightEntity.setDepartureDate(date);

        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date)).thenReturn(Optional.of(flightEntity));
        when(cargoRepository.getCargoEntityByFlightId(anyLong())).thenReturn(Optional.empty());

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertNotNull(result);
        assertEquals(0.0, result.getBaggageWeight());
        assertEquals(0.0, result.getCargoWeight());
        assertEquals(0.0, result.getTotalWeight());
        verify(flightRepository).getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date);
        verify(cargoRepository).getCargoEntityByFlightId(anyLong());
    }

    @Test
    void testGetFlightCargoDetail_emptyFlight() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = OffsetDateTime.now();

        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date)).thenReturn(Optional.empty());

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertNull(result);
        verify(flightRepository).getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date);
        verify(cargoRepository, never()).getCargoEntityByFlightId(anyLong());
    }

    @Test
    void testGetFlightCargoDetail_futureDepartureDate() {
        // Arrange
        int flightNumber = 1234;
        OffsetDateTime date = OffsetDateTime.now().plusDays(1);

        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(1L);
        flightEntity.setFlightNumber(flightNumber);
        flightEntity.setDepartureDate(date);

        when(flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date)).thenReturn(Optional.of(flightEntity));

        // Act
        FlightCargoDetailDto result = cargoServiceImpl.getFlightCargoDetail(flightNumber, date);

        // Assert
        assertNotNull(result);
        verify(flightRepository).getFlightEntityByFlightNumberAndDepartureDate(flightNumber, date);
    }

}
