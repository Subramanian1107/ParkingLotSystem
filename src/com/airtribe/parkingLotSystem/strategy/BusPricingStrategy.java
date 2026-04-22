package com.airtribe.parkingLotSystem.strategy;

public class BusPricingStrategy implements PricingStrategy {
    public double calculateFee(long hours) {
        return hours * 50;
    }
}
