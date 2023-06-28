package io.smart4aviation.service.util;

import io.smart4aviation.model.entity.FlightBaggage;
import io.smart4aviation.model.entity.FlightCargo;
import io.smart4aviation.model.enums.WeightUnit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaggageUtilTest {


    @Test
    void testCalculatePiecesWithEmptyList() {
        List<FlightBaggage> baggageList = new ArrayList<>();

        int expectedPieces = 0;
        int actualPieces = BaggageUtil.calculatePieces(baggageList);

        assertEquals(expectedPieces, actualPieces);
    }

    @Test
    void testCalculatePiecesWithOneElement() {

        FlightBaggage baggage = new FlightBaggage(null,null,3);
        List<FlightBaggage> baggageList = List.of(baggage);

        int expectedPieces = 3;
        int actualPieces = BaggageUtil.calculatePieces(baggageList);

        assertEquals(expectedPieces, actualPieces);
    }

    @Test
    void testCalculatePiecesWithMultipleElements() {

        FlightBaggage baggage1 = new FlightBaggage(null,null,3);
        FlightBaggage baggage2 = new FlightBaggage(null,null,5);
        FlightBaggage baggage3 = new FlightBaggage(null,null,2);
        List<FlightBaggage> baggageList = List.of(baggage1, baggage2, baggage3);

        int expectedPieces = 10;
        int actualPieces = BaggageUtil.calculatePieces(baggageList);

        assertEquals(expectedPieces, actualPieces);
    }

    @Test
    void testCalculateWeightWithEmptyList() {
        List<FlightCargo> cargoList = new ArrayList<>();
        WeightUnit weightUnit = WeightUnit.KG;
        double expectedWeight = 0.0;
        double actualWeight = BaggageUtil.calculateWeight(cargoList, weightUnit);

        assertEquals(expectedWeight, actualWeight, 0.001);
    }

    @Test
    void testCalculateWeightWithMultipleElements() {

        FlightCargo cargo1 = new FlightCargo(100.0, WeightUnit.KG, 1);
        FlightCargo cargo2 = new FlightCargo(50.0, WeightUnit.LB, 1);
        FlightCargo cargo3 = new FlightCargo(75.0, WeightUnit.KG, 1);
        List<FlightCargo> cargoList = List.of(cargo1, cargo2, cargo3);

        WeightUnit weightUnit = WeightUnit.KG;
        double expectedWeight = 197.6796;  // 100.0 + 22.6796 + 75.0
        double actualWeight = BaggageUtil.calculateWeight(cargoList, weightUnit);

        assertEquals(expectedWeight, actualWeight, 0.001);
    }

    @Test
    void testCalculateWeightWithSameWeightUnit() {
        List<FlightCargo> cargoList = new ArrayList<>();
        FlightCargo cargo1 = new FlightCargo();
        cargo1.setWeight(50.0);
        cargo1.setWeightUnit(WeightUnit.KG);
        FlightCargo cargo2 = new FlightCargo();
        cargo2.setWeight(25.0);
        cargo2.setWeightUnit(WeightUnit.KG);
        cargoList.add(cargo1);
        cargoList.add(cargo2);

        WeightUnit weightUnit = WeightUnit.KG;
        double expectedWeight = 75.0;  // 50.0 + 25.0
        double actualWeight = BaggageUtil.calculateWeight(cargoList, weightUnit);

        assertEquals(expectedWeight, actualWeight, 0.001);
    }

    @Test
    void testCalculateWeightWithDifferentWeightUnit() {

        FlightCargo cargo1 = new FlightCargo(100.0, WeightUnit.KG, 1);
        FlightCargo cargo2 = new FlightCargo(50.0, WeightUnit.LB, 1);
        List<FlightCargo> cargoList = List.of(cargo1, cargo2);

        WeightUnit weightUnit = WeightUnit.KG;
        double expectedWeight = 122.6796;  // 100.0 + 22.6796
        double actualWeight = BaggageUtil.calculateWeight(cargoList, weightUnit);

        assertEquals(expectedWeight, actualWeight, 0.001);
    }

    @Test
    void testCalculateWeightWithNullWeightOrUnit() {

        FlightCargo cargo1 = new FlightCargo(100.0, WeightUnit.KG, 1);
        FlightCargo cargo2 = new FlightCargo(null, WeightUnit.LB, 1);
        FlightCargo cargo3 = new FlightCargo(75.0, null, 1);
        List<FlightCargo> cargoList = List.of(cargo1, cargo2, cargo3);

        WeightUnit weightUnit = WeightUnit.KG;
        double expectedWeight = Double.NaN;
        double actualWeight = BaggageUtil.calculateWeight(cargoList, weightUnit);
        assertEquals(expectedWeight, actualWeight, 0.001);
    }

    @Test
    void testCalculatePiecesWithNullList() {
        List<FlightBaggage> baggageList = null;
        int expectedPieces = 0;
        int actualPieces = BaggageUtil.calculatePieces(baggageList);
        assertEquals(expectedPieces, actualPieces);
    }


    @Test
    void testCalculateWeightWithNullList() {
        List<FlightCargo> cargoList = null;
        WeightUnit weightUnit = WeightUnit.KG;
        double expectedWeight = 0.0;
        double actualWeight = BaggageUtil.calculateWeight(cargoList, weightUnit);
        assertEquals(expectedWeight, actualWeight, 0.001);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme