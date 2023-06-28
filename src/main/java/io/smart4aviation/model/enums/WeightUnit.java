package io.smart4aviation.model.enums;

/**
 * Represents a weight unit.
 */
public enum WeightUnit {

    KG(1, 2.20462262),
    LB(0.45359237, 1);

    /**
     * The conversion factor to kilograms.
     */
    private final double toKg;
    /**
     * The conversion factor to pounds.
     */
    private final double toLb;

    /**
     * Constructs a WeightUnit enum constant with the specified conversion factors.
     *
     * @param toKg the conversion factor to kilograms
     * @param toLb the conversion factor to pounds
     */
    WeightUnit(double toKg, double toLb) {
        this.toKg = toKg;
        this.toLb = toLb;
    }

    /**
     * Returns the conversion factor to kilograms.
     *
     * @return the conversion factor to kilograms
     */
    public double toKg() {
        return toKg;
    }

    /**
     * Returns the conversion factor to pounds.
     *
     * @return the conversion factor to pounds
     */
    public double toLb() {
        return toLb;
    }
}
