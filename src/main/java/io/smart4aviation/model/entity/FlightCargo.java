package io.smart4aviation.model.entity;

import io.smart4aviation.model.enums.WeightUnit;

/**
 * Represents flight cargo.
 */
public class FlightCargo extends AbstractFlightLoad {

    private Long id;

    /**
     * Constructs an empty FlightCargo object. (For Jackson Serialization)
     */
    public FlightCargo() {
    }

    /**
     * Constructs a FlightCargo object with the specified weight, weight unit, and number of pieces.
     *
     * @param weight      the weight of the flight cargo
     * @param weightUnit  the weight unit of the flight cargo
     * @param pieces      the number of pieces the flights' cargo
     */
    public FlightCargo(Double weight, WeightUnit weightUnit, Integer pieces) {
        super(weight, weightUnit, pieces);
    }

    /**
     * Constructs a FlightCargo object with the specified ID, weight, weight unit, and number of pieces.
     *
     * @param id          the ID of the flight cargo
     * @param weight      the weight of the flight cargo
     * @param weightUnit  the weight unit of the flight cargo
     * @param pieces      the number of pieces the flights' cargo
     */
    public FlightCargo(Long id, Double weight, WeightUnit weightUnit, Integer pieces) {
        super(weight, weightUnit, pieces);
        this.id = id;
    }

    /**
     * Returns the ID of the flight cargo.
     *
     * @return the ID of the flight cargo
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the flight cargo.
     *
     * @param id the ID to set for the flight cargo
     */
    public void setId(Long id) {
        this.id = id;
    }
}