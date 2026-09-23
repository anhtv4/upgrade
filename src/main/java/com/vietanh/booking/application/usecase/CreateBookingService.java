package com.vietanh.booking.application.usecase;

import com.vietanh.booking.domain.model.Booking;
import com.vietanh.booking.domain.model.CustomerId;
import com.vietanh.booking.domain.model.TimeSlot;
import com.vietanh.booking.domain.port.in.CreateBookingUseCase;
import com.vietanh.booking.domain.port.out.BookingRepository;
import com.vietanh.booking.domain.service.BookingDomainService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

/**
 * Implement inbound port, orchestrate domain service + outbound port.
 * Day la tang application - noi duy nhat "biet" ca domain lan port,
 * nhung van khong dung truc tiep JPA/HTTP.
 */
@Service
public class CreateBookingService implements CreateBookingUseCase {

    private final BookingRepository bookingRepository;
    private final BookingDomainService bookingDomainService;

    public CreateBookingService(BookingRepository bookingRepository, BookingDomainService bookingDomainService) {
        this.bookingRepository = bookingRepository;
        this.bookingDomainService = bookingDomainService;
    }

    @Override
    public Booking createBooking(UUID customerId, String serviceName, Instant start, Instant end) {
        CustomerId customer = new CustomerId(customerId);
        TimeSlot timeSlot = new TimeSlot(start, end);
        var existing = bookingRepository.findByCustomerId(customer);

        Booking booking = bookingDomainService.createBooking(customer, serviceName, timeSlot, existing);
        return bookingRepository.save(booking);
    }
}
