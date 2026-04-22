package com.airtribe.parkingLotSystem.service;


import com.airtribe.parkingLotSystem.entity.*;
import com.airtribe.parkingLotSystem.exception.NoSpotAvailableException;

import java.util.*;

public class SpotAllocationService {

    private List<Floor> floors;

    public SpotAllocationService(List<Floor> floors) {
        this.floors = floors;
    }

    public synchronized ParkingSpot allocateSpot(Vehicle vehicle) {

        for (Floor floor : floors) {
            for (SpotType type : getAllowed(vehicle.getType())) {

                Queue<ParkingSpot> spots = floor.getSpots(type);

                if (spots != null && !spots.isEmpty()) {
                    ParkingSpot spot = spots.poll();

                    if (spot.assignVehicle()) {
                        return spot;
                    }
                }
            }
        }

        throw new NoSpotAvailableException();
    }

    public void releaseSpot(ParkingSpot spot) {
        spot.freeSpot();

        for (Floor floor : floors) {
            if (floor.getFloorNumber() == spot.getFloorNumber()) {
                floor.getSpots(spot.getType()).offer(spot);
                return;
            }
        }
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