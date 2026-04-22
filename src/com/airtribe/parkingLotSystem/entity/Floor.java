package com.airtribe.parkingLotSystem.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Floor {

    private int floorNumber;
    private Map<SpotType, Queue<ParkingSpot>> availableSpots;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.availableSpots = new HashMap<>();
        for (SpotType type : SpotType.values()) {
            availableSpots.put(type, new LinkedList<>());
        }
    }

    public void addSpot(ParkingSpot spot) {
        availableSpots.get(spot.getType()).offer(spot);
    }

    public Queue<ParkingSpot> getSpots(SpotType type) {
        return availableSpots.get(type);
    }

    public int getFloorNumber() {
        return floorNumber;
    }
}