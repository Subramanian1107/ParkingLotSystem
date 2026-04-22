package com.airtribe.parkingLotSystem.strategy;

public class CarPricingStrategy implements PricingStrategy {
    public double calculateFee(long hours) {
        return hours * 20;
    }
}
