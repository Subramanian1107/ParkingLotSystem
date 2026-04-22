package com.airtribe.parkingLotSystem.controller;

import com.airtribe.parkingLotSystem.entity.*;
import com.airtribe.parkingLotSystem.exception.ParkingException;
import com.airtribe.parkingLotSystem.factory.VehicleFactory;
import com.airtribe.parkingLotSystem.service.ParkingService;

public class ParkingController {

    private ParkingService service;

    public ParkingController(ParkingService service) {
        this.service = service;
    }

    public ParkingTicket checkIn(String plate, VehicleType type) {
        try {
            Vehicle v = VehicleFactory.createVehicle(plate, type);
            return service.checkIn(v);
        } catch (ParkingException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public Double checkOut(String ticketId) {
        try {
            return service.checkOut(ticketId);
        } catch (ParkingException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}