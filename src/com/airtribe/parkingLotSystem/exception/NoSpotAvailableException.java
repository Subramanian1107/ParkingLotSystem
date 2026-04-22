package com.airtribe.parkingLotSystem.exception;


public class NoSpotAvailableException extends ParkingException {
    public NoSpotAvailableException() {
        super("No parking spots available");
    }
}