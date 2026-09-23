package com.vietanh.booking.domain.port.in;

import com.vietanh.booking.domain.model.Booking;

import java.time.Instant;
import java.util.UUID;

/** Inbound port: bien ben ngoai (web, cli, message consumer...) goi vao domain qua day. */
public interface CreateBookingUseCase {

    Booking createBooking(UUID customerId, String serviceName, Instant start, Instant end);
}
