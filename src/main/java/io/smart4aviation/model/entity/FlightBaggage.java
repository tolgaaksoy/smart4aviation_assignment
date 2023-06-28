package io.smart4aviation.model.entity;

import io.smart4aviation.model.enums.WeightUnit;

/**
 * Represents flight baggage.
 */
public class FlightBaggage extends AbstractFlightLoad {
    /**
     * The ID of the flight baggage.
     */
    private Long id;

    /**
     * Constructs an empty FlightBaggage object. (For Jackson Serialization)
     */
    public FlightBaggage() {
    }

    /**
     * Constructs a FlightBaggage object with the specified weight, weight unit, and number of pieces.
     *
     * @param weight      the weight of the flight baggage
     * @param weightUnit  the weight unit of the flight baggage
     * @param pieces      the number of pieces the flights' baggage
     */
    public FlightBaggage(Double weight, WeightUnit weightUnit, Integer pieces) {
        super(weight, weightUnit, pieces);
    }

    /**
     * Constructs a FlightBaggage object with the specified ID, weight, weight unit, and number of pieces.
     *
     * @param id          the ID of the flight baggage
     * @param weight      the weight of the flight baggage
     * @param weightUnit  the weight unit of the flight baggage
     * @param pieces      the number of pieces of the flight baggage
     */
    public FlightBaggage(Long id, Double weight, WeightUnit weightUnit, Integer pieces) {
        super(weight, weightUnit, pieces);
        this.id = id;
    }

    /**
     * Returns the ID of the flight baggage.
     *
     * @return the ID of the flight baggage
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the flight baggage.
     *
     * @param id the ID to set for the flight baggage
     */
    public void setId(Long id) {
        this.id = id;
    }
}
