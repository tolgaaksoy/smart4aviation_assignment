package io.smart4aviation.model.entity;

import io.smart4aviation.model.enums.WeightUnit;

/**
 * Represents an abstract flight load.
 */
public abstract class AbstractFlightLoad {

    /**
     * The weight of the flights' load.
     */
    private Double weight;

    /**
     * The weight unit of the flights' load.
     */
    private WeightUnit weightUnit;

    /**
     * The number of pieces the flights' load.
     */
    private Integer pieces;

    /**
     * Constructs an AbstractFlightLoad object.
     */
    protected AbstractFlightLoad() {
    }

    /**
     * Constructs an AbstractFlightLoad object with the specified weight, weight unit, and number of pieces.
     *
     * @param weight     The weight of the flights' load.
     * @param weightUnit The weight unit of the flights' load.
     * @param pieces     The number of pieces the flights' load.
     */
    protected AbstractFlightLoad(Double weight, WeightUnit weightUnit, Integer pieces) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.pieces = pieces;
    }

    /**
     * Returns the weight of the flights' load.
     *
     * @return The weight of the flights' load.
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the flights' load.
     *
     * @param weight The weight of the flights' load.
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Returns the weight of the flights' load in the specified weight unit.
     *
     * @param weightUnit The weight unit to convert to.
     * @return The weight of the flights' load in the specified weight unit.
     */
    public Double getWeight(WeightUnit weightUnit) {
        if (this.weight == null || weightUnit == null) {
            System.out.println("Weight or weight unit is null");
        } else if (this.weightUnit == weightUnit) {
            return weight;
        } else if (this.weightUnit == WeightUnit.KG && weightUnit == WeightUnit.LB) {
            return weight * WeightUnit.KG.toLb();
        } else if (this.weightUnit == WeightUnit.LB && weightUnit == WeightUnit.KG) {
            return weight * WeightUnit.LB.toKg();
        }
        return Double.NaN;
    }

    /**
     * Returns the weight unit of the flights' load.
     *
     * @return The weight unit of the flights' load.
     */
    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    /**
     * Sets the weight unit of the flights' load.
     *
     * @param weightUnit The weight unit of the flights' load.
     */
    public void setWeightUnit(WeightUnit weightUnit) {
        this.weightUnit = weightUnit;
    }

    /**
     * Returns the number of pieces the flights' load.
     *
     * @return The number of pieces the flights' load.
     */
    public Integer getPieces() {
        return pieces;
    }

    /**
     * Sets the number of pieces the flights' load.
     *
     * @param pieces The number of pieces the flights' load.
     */
    public void setPieces(Integer pieces) {
        this.pieces = pieces;
    }
}
