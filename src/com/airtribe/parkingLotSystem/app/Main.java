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

        for (int i = 1; i <= 5; i++) {
            spots.get(SpotType.MOTORCYCLE).offer(new ParkingSpot("B" + i, SpotType.MOTORCYCLE));
            spots.get(SpotType.CAR).offer(new ParkingSpot("C" + i, SpotType.CAR));
        }
        for (int i = 1; i <= 2; i++) {
            spots.get(SpotType.BUS).offer(new ParkingSpot("BUS" + i, SpotType.BUS));
        }

        // Services wiring
        SpotAllocationService alloc = new SpotAllocationService(spots);
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

                    System.out.print("Type (1-Bike, 2-Car, 3-Bus): ");
                    int type = sc.nextInt();

                    VehicleType vt = (type == 1) ? VehicleType.MOTORCYCLE :
                            (type == 2) ? VehicleType.CAR :
                                    VehicleType.BUS;

                    ParkingTicket ticket = controller.checkIn(plate, vt);
                    if (ticket != null) {
                        System.out.println("Allocated Spot: " + ticket.getSpot().getSpotId());
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