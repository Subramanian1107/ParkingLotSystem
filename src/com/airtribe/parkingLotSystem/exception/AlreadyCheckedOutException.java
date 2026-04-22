package com.airtribe.parkingLotSystem.exception;

public class AlreadyCheckedOutException extends ParkingException {
    public AlreadyCheckedOutException(String ticketId) {
        super("Ticket already checked out: " + ticketId);
    }
}
