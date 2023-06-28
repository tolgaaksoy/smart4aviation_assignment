package io.smart4aviation.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

/**
 * Represents a cargo entity.
 */
public class CargoEntity {
    /**
     * The ID of the flight.
     */
    private Long flightId;
    /**
     * The list of flight cargo.
     */
    @JsonProperty("cargo") // For JSON deserialization
    private List<FlightCargo> cargoList;
    /**
     * The list of flight baggage.
     */
    @JsonProperty("baggage") // For JSON deserialization
    private List<FlightBaggage> baggageList;

    /**
     * Constructs an empty CargoEntity object. For JSON deserialization.
     */
    public CargoEntity() {
    }

    /**
     * Constructs a CargoEntity object with the specified flight ID, cargo list, and baggage list.
     *
     * @param flightId     the ID of the flight
     * @param cargoList    the list of flight cargo
     * @param baggageList  the list of flight baggage
     */
    public CargoEntity(Long flightId, List<FlightCargo> cargoList, List<FlightBaggage> baggageList) {
        this.flightId = flightId;
        this.cargoList = cargoList;
        this.baggageList = baggageList;
    }

    /**
     * Returns the ID of the flight.
     *
     * @return the flight ID
     */
    public Long getFlightId() {
        return flightId;
    }

    /**
     * Sets the ID of the flight.
     *
     * @param flightId the flight ID to set
     */
    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    /**
     * Returns the list of flight cargo.
     *
     * @return the list of flight cargo
     */
    public List<FlightCargo> getCargoList() {
        if (cargoList == null) return Collections.emptyList();
        return Collections.unmodifiableList(cargoList);
    }

    /**
     * Sets the list of flight cargo.
     *
     * @param cargoList the list of flight cargo to set
     */
    public void setCargoList(List<FlightCargo> cargoList) {
        this.cargoList = cargoList;
    }

    /**
     * Returns the list of flight baggage.
     *
     * @return the list of flight baggage
     */
    public List<FlightBaggage> getBaggageList() {
        if (baggageList == null) return Collections.emptyList();
        return Collections.unmodifiableList(baggageList);
    }

    /**
     * Sets the list of flight baggage.
     *
     * @param baggageList the list of flight baggage to set
     */
    public void setBaggageList(List<FlightBaggage> baggageList) {
        this.baggageList = baggageList;
    }
}
