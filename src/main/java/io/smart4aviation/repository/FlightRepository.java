package io.smart4aviation.repository;

import io.smart4aviation.model.entity.FlightEntity;
import io.smart4aviation.repository.util.FileReader;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Repository class for accessing flight data.
 */
public class FlightRepository {
    private static List<FlightEntity> flightEntities;

    private FlightRepository() {
        //throw new AssertionError("This class should not be instantiated");
    }

    private static class FlightRepositoryHolder {
        private static final FlightRepository INSTANCE = new FlightRepository();

        static {
            FileReader fileReader = FileReader.getInstance();
            flightEntities = fileReader.readFlightEntityFromFile();
        }
    }

    /**
     * Returns the instance of the FlightRepository.
     *
     * @return FlightRepository instance
     */
    public static FlightRepository getInstance() {
        return FlightRepository.FlightRepositoryHolder.INSTANCE;
    }

    /**
     * Retrieves the flight entity associated with the given flight number and departure date.
     *
     * @param flightNumber the flight number
     * @param date         the departure date
     * @return Optional containing the FlightEntity if found, or an empty Optional if not found
     */
    public Optional<FlightEntity> getFlightEntityByFlightNumberAndDepartureDate(int flightNumber, OffsetDateTime date) {
        for (FlightEntity flightEntity : flightEntities) {
            if (flightEntity.getFlightNumber() == flightNumber && flightEntity.getDepartureDate().toLocalDate().equals(date.toLocalDate())) {
                return Optional.of(flightEntity);
            }
        }
        System.out.println("Flight entity not found");
        return Optional.empty(); // Flight entity not found
    }

    /**
     * Retrieves all flight entities that match the given departure airport IATA code, arrival airport IATA code, and departure date.
     *
     * @param departureAirportIATACode the departure airport IATA code
     * @param arrivalAirportIATACode   the arrival airport IATA code
     * @param date                    the departure date
     * @return List of FlightEntity objects that match the criteria, or an empty list if no entities are found
     */
    public List<FlightEntity> getAllFlightEntityByDepartureAirportIATACodeOrArrivalAirportIATACodeAndDepartureDate(String departureAirportIATACode, String arrivalAirportIATACode, OffsetDateTime date) {
        List<FlightEntity> flightEntityList = new ArrayList<>();
        for (FlightEntity flightEntity : flightEntities) {
            if ((flightEntity.getDepartureAirportIATACode().equals(departureAirportIATACode) || flightEntity.getArrivalAirportIATACode().equals(arrivalAirportIATACode))
                    && flightEntity.getDepartureDate().toLocalDate().equals(date.toLocalDate())) {
                flightEntityList.add(flightEntity);
            }
        }
        if (flightEntityList.isEmpty()) {
            System.out.println("Flight entity not found");
            return Collections.emptyList(); // Flight entity not found
        }
        return Collections.unmodifiableList(flightEntityList);
    }

    /**
     * !!! THIS CONSTRUCTOR IS USED FOR TESTING PURPOSES ONLY !!!
     * Set the list of flight entities.
     *
     * @param flightEntities the list of flight entities
     */
    public static void setFlightEntities(List<FlightEntity> flightEntities) {
        FlightRepository.flightEntities = flightEntities;
    }
}