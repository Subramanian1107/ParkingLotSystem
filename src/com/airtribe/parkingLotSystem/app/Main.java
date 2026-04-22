package com.airtribe.parkingLotSystem.app;


import com.airtribe.parkingLotSystem.controller.ParkingController;
import com.airtribe.parkingLotSystem.entity.*;
import com.airtribe.parkingLotSystem.repository.*;
import com.airtribe.parkingLotSystem.service.*;
import com.airtribe.parkingLotSystem.strategy.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Setup spots
        Map<SpotType, Queue<ParkingSpot>> spots = new HashMap<>();
        spots.put(SpotType.MOTORCYCLE, new LinkedList<>());
        spots.put(SpotType.CAR, new LinkedList<>());
        spots.put(SpotType.BUS, new LinkedList<>());

        // Create floors
        List<Floor> floors = new ArrayList<>();

        for (int f = 1; f <= 2; f++) {
            Floor floor = new Floor(f);

            // Add spots per floor
            for (int i = 1; i <= 3; i++) {
                floor.addSpot(new ParkingSpot("F" + f + "-B" + i, SpotType.MOTORCYCLE, f));
                floor.addSpot(new ParkingSpot("F" + f + "-C" + i, SpotType.CAR, f));
            }

            for (int i = 1; i <= 1; i++) {
                floor.addSpot(new ParkingSpot("F" + f + "-BUS" + i, SpotType.BUS, f));
            }

            floors.add(floor);
        }

        // Inject into allocation service
        SpotAllocationService alloc = new SpotAllocationService(floors);
        TicketRepository repo = new InMemoryTicketRepository();

        Map<VehicleType, PricingStrategy> strategyMap = new HashMap<>();
        strategyMap.put(VehicleType.CAR, new CarPricingStrategy());
        strategyMap.put(VehicleType.MOTORCYCLE, new BikePricingStrategy());
        strategyMap.put(VehicleType.BUS, new BusPricingStrategy());

        PricingService pricing = new PricingService(strategyMap);

        ParkingService parkingService = new ParkingService(alloc, repo, pricing);
        ParkingController controller = new ParkingController(parkingService);

        // Menu loop
        while (true) {
            System.out.println("\n1. Check In");
            System.out.println("2. Check Out");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Plate: ");
                    String plate = sc.next();

                    System.out.print("Enter Vehicle Type (MOTORCYCLE/CAR/BUS): ");
                    String typeInput = sc.next();

                    ParkingTicket ticket = controller.checkIn(plate, typeInput);
                    if (ticket != null) {
                        System.out.println("Allocated Spot: " + ticket.getSpot().getSpotId());
                        System.out.println("Floor: " + ticket.getSpot().getFloorNumber());
                        System.out.println("Ticket ID: " + ticket.getTicketId());
                    }
                    break;

                case 2:
                    System.out.print("Enter Ticket ID: ");
                    String id = sc.next();

                    Double fee = controller.checkOut(id);
                    if (fee != null) {
                        System.out.println("Fee: " + fee);
                    }
                    break;

                case 3:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}