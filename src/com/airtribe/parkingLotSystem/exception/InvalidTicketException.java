package com.airtribe.parkingLotSystem.exception;

public class InvalidTicketException extends ParkingException {
    public InvalidTicketException(String ticketId) {
        super("Invalid ticket ID: " + ticketId);
    }
}
