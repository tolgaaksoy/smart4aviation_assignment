package io.smart4aviation.service.util;

import io.smart4aviation.model.entity.AbstractFlightLoad;
import io.smart4aviation.model.enums.WeightUnit;

import java.util.List;

/**
 * Utility class for baggage-related calculations.
 */
public class BaggageUtil {

    private BaggageUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Calculates the total number of baggage pieces from a list of flight loads.
     *
     * @param flightLoadList the list of flight loads
     * @return the total number of baggage pieces
     */
    public static int calculatePieces(List<? extends AbstractFlightLoad> flightLoadList) {
        if (flightLoadList == null) {
            return 0;
        }
        int baggagePieces = 0;
        for (AbstractFlightLoad baggage : flightLoadList) {
            baggagePieces += baggage.getPieces();
        }
        return baggagePieces;
    }

    /**
     * Calculates the total weight of baggage from a list of flight loads, using the specified weight unit.
     *
     * @param flightLoadList the list of flight loads
     * @param weightUnit     the weight unit to use for calculation
     * @return the total weight of baggage
     */
    public static Double calculateWeight(List<? extends AbstractFlightLoad> flightLoadList, WeightUnit weightUnit) {
        if (flightLoadList == null || weightUnit == null) {
            return 0.0;
        }
        return flightLoadList.stream()
                .mapToDouble(flightLoad -> flightLoad.getWeight(weightUnit))
                .sum();
    }
}