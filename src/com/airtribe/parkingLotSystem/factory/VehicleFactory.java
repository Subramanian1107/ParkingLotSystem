package com.airtribe.parkingLotSystem.factory;

import com.airtribe.parkingLotSystem.entity.Vehicle;
import com.airtribe.parkingLotSystem.entity.VehicleType;

public class VehicleFactory {

    public static Vehicle createVehicle(String plate, VehicleType type) {
        return new Vehicle(plate, type);
    }
}