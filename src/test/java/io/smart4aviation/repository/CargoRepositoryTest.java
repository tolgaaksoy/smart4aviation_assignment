package io.smart4aviation.repository;

import io.smart4aviation.model.entity.CargoEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CargoRepositoryTest {
    @Mock
    List<CargoEntity> cargoEntities;
    @InjectMocks
    CargoRepository cargoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cargoEntities = new ArrayList<>();
        cargoRepository.setCargoEntities(cargoEntities);
    }

    @Test
    void testGetInstance() {
        CargoRepository instance1 = CargoRepository.getInstance();
        CargoRepository instance2 = CargoRepository.getInstance();

        Assertions.assertNotNull(instance1);
        Assertions.assertNotNull(instance2);
        Assertions.assertSame(instance1, instance2);
    }

    @Test
    void testGetCargoEntityByFlightId_cargoExists() {
        // Arrange
        long flightId = 1L;
        CargoEntity expectedCargoEntity = new CargoEntity();
        expectedCargoEntity.setFlightId(flightId);

        cargoEntities.add(cargoEntities.size(), expectedCargoEntity);

        // Act
        Optional<CargoEntity> result = cargoRepository.getCargoEntityByFlightId(flightId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedCargoEntity, result.get());
    }

    @Test
    void testGetCargoEntityByFlightId_emptyCargoEntities() {
        // Arrange
        long flightId = 1L;
        cargoRepository.setCargoEntities(Collections.emptyList());

        // Act
        Optional<CargoEntity> result = cargoRepository.getCargoEntityByFlightId(flightId);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testGetCargoEntityByFlightId_multipleCargoEntitiesExist() {
        // Arrange
        long flightId = 1L;
        CargoEntity cargoEntity1 = new CargoEntity();
        cargoEntity1.setFlightId(flightId);
        CargoEntity cargoEntity2 = new CargoEntity();
        cargoEntity2.setFlightId(flightId);

        cargoEntities.add(cargoEntity1);
        cargoEntities.add(cargoEntity2);

        // Act
        Optional<CargoEntity> result = cargoRepository.getCargoEntityByFlightId(flightId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(cargoEntity1, result.get());
    }

    @Test
    void testGetCargoEntityByFlightId_flightIdNotFound() {
        // Arrange
        long flightId = 1L;
        CargoEntity cargoEntity = new CargoEntity();
        cargoEntity.setFlightId(2L);

        cargoEntities.add(cargoEntity);

        // Act
        Optional<CargoEntity> result = cargoRepository.getCargoEntityByFlightId(flightId);

        // Assert
        assertFalse(result.isPresent());
    }

}