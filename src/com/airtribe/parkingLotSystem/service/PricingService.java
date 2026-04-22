package com.airtribe.parkingLotSystem.service;

import com.airtribe.parkingLotSystem.entity.VehicleType;
import com.airtribe.parkingLotSystem.strategy.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class PricingService {

    private Map<VehicleType, PricingStrategy> strategies;

    public PricingService(Map<VehicleType, PricingStrategy> strategies) {
        this.strategies = strategies;
    }

    public double calculate(VehicleType type, LocalDateTime entry, LocalDateTime exit) {
        long hours = (long) Math.ceil(Duration.between(entry, exit).toMinutes() / 60.0);
        return strategies.get(type).calculateFee(hours);
    }
}
