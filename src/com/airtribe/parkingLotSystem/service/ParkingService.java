package com.airtribe.parkingLotSystem.service;

import com.airtribe.parkingLotSystem.entity.*;
import com.airtribe.parkingLotSystem.exception.AlreadyCheckedOutException;
import com.airtribe.parkingLotSystem.exception.InvalidTicketException;
import com.airtribe.parkingLotSystem.repository.TicketRepository;
import com.airtribe.parkingLotSystem.util.IdGenerator;

public class ParkingService {

    private SpotAllocationService allocationService;
    private TicketRepository repo;
    private PricingService pricingService;

    public ParkingService(SpotAllocationService allocationService,
                          TicketRepository repo,
                          PricingService pricingService) {
        this.allocationService = allocationService;
        this.repo = repo;
        this.pricingService = pricingService;
    }

    public ParkingTicket checkIn(Vehicle vehicle) {
        ParkingSpot spot = allocationService.allocateSpot(vehicle);

        String ticketId = IdGenerator.generateTicketId();

        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, spot);
        repo.save(ticket);

        return ticket;
    }

    public double checkOut(String ticketId) {

        ParkingTicket ticket = repo.findById(ticketId);

        if (ticket == null) {
            throw new InvalidTicketException(ticketId);
        }

        if (ticket.getStatus() == TicketStatus.COMPLETED) {
            throw new AlreadyCheckedOutException(ticketId);
        }

        ticket.closeTicket();
        allocationService.releaseSpot(ticket.getSpot());

        return pricingService.calculate(
                ticket.getVehicle().getType(),
                ticket.getEntryTime(),
                ticket.getExitTime()
        );
    }
}
