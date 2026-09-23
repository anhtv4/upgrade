package com.vietanh.booking.domain.model;

import java.util.UUID;

public record BookingId(UUID value) {

    public static BookingId newId() {
        return new BookingId(UUID.randomUUID());
    }
}
