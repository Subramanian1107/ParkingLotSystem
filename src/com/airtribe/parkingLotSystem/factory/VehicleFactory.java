package com.airtribe.parkingLotSystem.factory;

import com.airtribe.parkingLotSystem.entity.Vehicle;
import com.airtribe.parkingLotSystem.entity.VehicleType;
import com.airtribe.parkingLotSystem.exception.InvalidVehicleTypeException;

public class VehicleFactory {

    public static Vehicle createVehicle(String plate, String typeInput) {

        VehicleType type;

        try {
            type = VehicleType.valueOf(typeInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidVehicleTypeException(typeInput);
        }

        return new Vehicle(plate, type);
    }
}