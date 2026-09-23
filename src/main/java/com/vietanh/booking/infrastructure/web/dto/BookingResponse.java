package com.vietanh.booking.infrastructure.web.dto;

import com.vietanh.booking.domain.model.Booking;

import java.time.Instant;
import java.util.UUID;

public record BookingResponse(
        UUID id,
        UUID customerId,
        String serviceName,
        Instant start,
        Instant end,
        String status
) {
    public static BookingResponse from(Booking booking) {
        return new BookingResponse(
                booking.id().value(),
                booking.customerId().value(),
                booking.serviceName(),
                booking.timeSlot().start(),
                booking.timeSlot().end(),
                booking.status().name()
        );
    }
}
