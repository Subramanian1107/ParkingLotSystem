package com.airtribe.parkingLotSystem.exception;

public class InvalidVehicleTypeException extends ParkingException {

    public InvalidVehicleTypeException(String input) {
        super("Invalid vehicle type: " + input + ". Allowed types: MOTORCYCLE, CAR, BUS");
    }
}
