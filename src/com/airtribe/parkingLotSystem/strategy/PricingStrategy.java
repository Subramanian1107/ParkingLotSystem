package com.airtribe.parkingLotSystem.strategy;

public interface PricingStrategy {
    double calculateFee(long hours);
}