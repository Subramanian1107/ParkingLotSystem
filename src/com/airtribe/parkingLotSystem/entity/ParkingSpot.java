package com.airtribe.parkingLotSystem.entity;

public class ParkingSpot {

    private String spotId;
    private SpotType type;
    private boolean isAvailable = true;
    private int floorNumber;

    public ParkingSpot(String spotId, SpotType type, int floorNumber) {
        this.spotId = spotId;
        this.type = type;
        this.floorNumber = floorNumber;
    }

    public synchronized boolean assignVehicle() {
        if (!isAvailable) return false;
        isAvailable = false;
        return true;
    }

    public synchronized void freeSpot() {
        isAvailable = true;
    }

    public String getSpotId() { return spotId; }
    public SpotType getType() { return type; }
    public int getFloorNumber() { return floorNumber; }
}