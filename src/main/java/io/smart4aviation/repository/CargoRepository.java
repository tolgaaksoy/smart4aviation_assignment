package io.smart4aviation.repository;

import io.smart4aviation.model.entity.CargoEntity;
import io.smart4aviation.repository.util.FileReader;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for accessing cargo data.
 */
public class CargoRepository {

    private static List<CargoEntity> cargoEntities;

    private CargoRepository() {
        //throw new AssertionError("This class should not be instantiated");
    }

    /**
     * Returns the instance of the CargoRepository.
     *
     * @return CargoRepository instance
     */
    public static CargoRepository getInstance() {
        return CargoRepositoryHolder.INSTANCE;
    }

    private static class CargoRepositoryHolder {
        private static final CargoRepository INSTANCE = new CargoRepository();

        static {
            FileReader fileReader = FileReader.getInstance();

            cargoEntities = fileReader.readCargoEntityFromFile();
        }
    }

    /**
     * Retrieves the cargo entity associated with the given flight ID.
     *
     * @param flightId the flight ID
     * @return Optional containing the CargoEntity if found, or an empty Optional if not found
     */
    public Optional<CargoEntity> getCargoEntityByFlightId(long flightId) {
        for (CargoEntity cargoEntity : cargoEntities) {
            if (cargoEntity.getFlightId() == flightId) {
                return Optional.of(cargoEntity);
            }
        }
        System.out.println("Cargo entity not found");
        return Optional.empty(); // Cargo entity not found
    }

    /**
     * !!! THIS CONSTRUCTOR IS USED FOR TESTING PURPOSES ONLY !!!
     * Set the list of cargo entities.
     *
     * @param cargoEntities the list of cargo entities
     */
    public static void setCargoEntities(List<CargoEntity> cargoEntities) {
        CargoRepository.cargoEntities = cargoEntities;
    }
}