package com.airtribe.parkingLotSystem.repository;


import com.airtribe.parkingLotSystem.entity.ParkingTicket;

public interface TicketRepository {
    void save(ParkingTicket ticket);
    ParkingTicket findById(String ticketId);
}