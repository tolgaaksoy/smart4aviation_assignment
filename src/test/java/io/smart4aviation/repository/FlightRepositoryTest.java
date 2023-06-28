package io.smart4aviation.repository;

import io.smart4aviation.model.entity.FlightEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class FlightRepositoryTest {
    @Mock
    List<FlightEntity> flightEntities;
    @InjectMocks
    FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        flightEntities = new ArrayList<>();
        flightRepository.setFlightEntities(flightEntities);
    }

    @Test
    void testGetInstance() {
        FlightRepository instance1 = FlightRepository.getInstance();
        FlightRepository instance2 = FlightRepository.getInstance();

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);
        Assertions.assertSame(instance1, instance2);
    }


    @Test
    void testGetFlightEntityByFlightNumberAndDepartureDate_ReturnsEmptyOptional() {
        int flightNumber = 123;
        OffsetDateTime departureDate = OffsetDateTime.now();

        Optional<FlightEntity> result = flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, departureDate);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testGetFlightEntityByFlightNumberAndDepartureDate_ReturnsCorrectFlightEntity() {
        int flightNumber = 123;
        OffsetDateTime departureDate = OffsetDateTime.now();
        FlightEntity expectedFlightEntity = new FlightEntity();
        expectedFlightEntity.setFlightNumber(flightNumber);
        expectedFlightEntity.setDepartureDate(departureDate);
        flightEntities.add(expectedFlightEntity);

        Optional<FlightEntity> result = flightRepository.getFlightEntityByFlightNumberAndDepartureDate(flightNumber, departureDate);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expectedFlightEntity, result.get());
    }


    @Test
    void testGetAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate_ReturnsEmptyList() {
        String departureAirportIATACode = "ABC";
        String arrivalAirportIATACode = "XYZ";
        OffsetDateTime departureDate = OffsetDateTime.now();

        List<FlightEntity> result = flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(departureAirportIATACode, arrivalAirportIATACode, departureDate);

        Assertions.assertTrue(result.isEmpty());
    }


    @Test
    void testGetAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate_ReturnsFlightEntityList() {
        String departureAirportIATACode = "ABC";
        String arrivalAirportIATACode = "XYZ";
        OffsetDateTime departureDate = OffsetDateTime.now();

        FlightEntity flightEntity1 = new FlightEntity();
        flightEntity1.setDepartureAirportIATACode(departureAirportIATACode);
        flightEntity1.setArrivalAirportIATACode(arrivalAirportIATACode);
        flightEntity1.setDepartureDate(departureDate);

        FlightEntity flightEntity2 = new FlightEntity();
        flightEntity2.setDepartureAirportIATACode(arrivalAirportIATACode);
        flightEntity2.setArrivalAirportIATACode(departureAirportIATACode);
        flightEntity2.setDepartureDate(departureDate);

        flightEntities.add(flightEntity1);
        flightEntities.add(flightEntity2);

        List<FlightEntity> result = flightRepository.getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(departureAirportIATACode, arrivalAirportIATACode, departureDate);

        Assertions.assertEquals(1, result.size());
        Assertions.assertTrue(result.contains(flightEntity1));
        Assertions.assertFalse(result.contains(flightEntity2));
    }

}