package com.airtribe.parkingLotSystem.repository;

import com.airtribe.parkingLotSystem.entity.ParkingTicket;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTicketRepository implements TicketRepository {

    private ConcurrentHashMap<String, ParkingTicket> storage = new ConcurrentHashMap<>();

    public void save(ParkingTicket ticket) {
        storage.put(ticket.getTicketId(), ticket);
    }

    public ParkingTicket findById(String ticketId) {
        return storage.get(ticketId);
    }
}