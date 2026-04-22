package com.airtribe.parkingLotSystem.service;


import com.airtribe.parkingLotSystem.entity.*;
import com.airtribe.parkingLotSystem.exception.NoSpotAvailableException;

import java.util.*;

public class SpotAllocationService {

    private Map<SpotType, Queue<ParkingSpot>> availableSpots;

    public SpotAllocationService(Map<SpotType, Queue<ParkingSpot>> availableSpots) {
        this.availableSpots = availableSpots;
    }

    public synchronized ParkingSpot allocateSpot(Vehicle vehicle) {
        for (SpotType type : getAllowed(vehicle.getType())) {
            Queue<ParkingSpot> q = availableSpots.get(type);

            if (q != null && !q.isEmpty()) {
                ParkingSpot spot = q.poll();
                if (spot.assignVehicle()) return spot;
            }
        }
        throw new NoSpotAvailableException();
    }

    public void releaseSpot(ParkingSpot spot) {
        spot.freeSpot();
        availableSpots.get(spot.getType()).offer(spot);
    }

    private List<SpotType> getAllowed(VehicleType type) {
        switch (type) {
            case MOTORCYCLE:
                return Arrays.asList(SpotType.MOTORCYCLE, SpotType.CAR, SpotType.BUS);
            case CAR:
                return Arrays.asList(SpotType.CAR, SpotType.BUS);
            default:
                return Arrays.asList(SpotType.BUS);
        }
    }
}