package com.vietanh.booking.infrastructure.web;

import com.vietanh.booking.domain.model.Booking;
import com.vietanh.booking.domain.port.in.CreateBookingUseCase;
import com.vietanh.booking.infrastructure.web.dto.BookingResponse;
import com.vietanh.booking.infrastructure.web.dto.CreateBookingRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final CreateBookingUseCase createBookingUseCase;

    public BookingController(CreateBookingUseCase createBookingUseCase) {
        this.createBookingUseCase = createBookingUseCase;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> create(@Valid @RequestBody CreateBookingRequest request) {
        Booking booking = createBookingUseCase.createBooking(
                request.customerId(), request.serviceName(), request.start(), request.end());
        return ResponseEntity.status(HttpStatus.CREATED).body(BookingResponse.from(booking));
    }
}
