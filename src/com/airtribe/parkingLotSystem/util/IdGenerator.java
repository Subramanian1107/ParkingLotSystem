package com.airtribe.parkingLotSystem.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private static final AtomicLong counter = new AtomicLong(1);

    public static String generateTicketId() {
        return "T" + counter.getAndIncrement();
    }
}
