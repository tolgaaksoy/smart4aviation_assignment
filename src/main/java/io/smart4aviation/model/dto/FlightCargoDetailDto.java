package io.smart4aviation.model.dto;

import io.smart4aviation.model.enums.WeightUnit;

import java.time.OffsetDateTime;

/**
 * Represents the data transfer object for flight cargo details.
 */
public class FlightCargoDetailDto {
    /**
     * The flight number.
     */
    private final Integer flightNumber;

    /**
     * The date of the flight.
     */
    private final OffsetDateTime date;

    /**
     * The weight unit used.
     */
    private final WeightUnit weightUnit;

    /**
     * The baggage weight.
     */
    private Double baggageWeight = 0.0;

    /**
     * The cargo weight.
     */
    private Double cargoWeight = 0.0;

    /**
     * The total weight (baggage weight + cargo weight).
     */
    private Double totalWeight = 0.0;

    /**
     * Constructs a FlightCargoDetailDto object with the specified flight number, date, and weight unit.
     *
     * @param flightNumber The flight number.
     * @param date The date of the flight.
     * @param weightUnit The weight unit used.
     */
    public FlightCargoDetailDto(Integer flightNumber, OffsetDateTime date, WeightUnit weightUnit) {
        this.flightNumber = flightNumber;
        this.date = date;
        this.weightUnit = weightUnit;
    }

    /**
     * Returns the flight number.
     *
     * @return The flight number.
     */
    public Integer getFlightNumber() {
        return flightNumber;
    }

    /**
     * Returns the date of the flight.
     *
     * @return The date of the flight.
     */
    public OffsetDateTime getDate() {
        return date;
    }

    /**
     * Returns the weight unit used.
     *
     * @return The weight unit used.
     */
    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    /**
     * Returns the baggage weight.
     *
     * @return The baggage weight.
     */
    public Double getBaggageWeight() {
        return baggageWeight;
    }

    /**
     * Sets the baggage weight.
     *
     * @param baggageWeight The baggage weight.
     */
    public void setBaggageWeight(Double baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    /**
     * Returns the cargo weight.
     *
     * @return The cargo weight.
     */
    public Double getCargoWeight() {
        return cargoWeight;
    }

    /**
     * Sets the cargo weight.
     *
     * @param cargoWeight The cargo weight.
     */
    public void setCargoWeight(Double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    /**
     * Returns the total weight (baggage weight + cargo weight).
     *
     * @return The total weight.
     */
    public Double getTotalWeight() {
        return totalWeight;
    }

    /**
     * Sets the total weight (baggage weight + cargo weight).
     *
     * @param totalWeight The total weight.
     */
    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    /**
     * Calculates the total weight by adding the baggage weight and cargo weight.
     */
    public void calculateTotalWeight() {
        this.totalWeight = this.baggageWeight + this.cargoWeight;
    }

    /**
     * Returns a string representation of the FlightCargoDetailDto object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Flight Cargo Details\n" +
                "Flight Number  : " + flightNumber + "\n" +
                "Date           : " + date + "\n" +
                "Baggage Weight : " + baggageWeight + " " + weightUnit + "\n" +
                "Cargo Weight   : " + cargoWeight + " " + weightUnit + "\n" +
                "Total Weight   : " + totalWeight + " " + weightUnit;
    }
}