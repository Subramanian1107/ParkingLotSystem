package com.airtribe.parkingLotSystem.strategy;

public class BikePricingStrategy implements PricingStrategy {
    public double calculateFee(long hours) {
        return hours * 10;
    }
}
