package com.vietanh.booking.domain.service;

import com.vietanh.booking.domain.model.Booking;
import com.vietanh.booking.domain.model.CustomerId;
import com.vietanh.booking.domain.model.TimeSlot;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Khong co @SpringBootTest, khong can ApplicationContext.
// Day chinh la loi ich cua Hexagonal Architecture: domain logic test truc tiep,
// chay trong vai mili-giay - se thay ro gia tri hon o Phase 6.
class BookingDomainServiceTest {

    private final BookingDomainService service = new BookingDomainService(new NoOverlapConflictPolicy());

    @Test
    void tao_booking_thanh_cong_khi_khong_trung_lich() {
        CustomerId customer = new CustomerId(UUID.randomUUID());
        Instant start = Instant.now().plus(1, ChronoUnit.DAYS);
        TimeSlot slot = new TimeSlot(start, start.plus(1, ChronoUnit.HOURS));

        Booking booking = service.createBooking(customer, "Cat toc", slot, List.of());

        assertNotNull(booking.id());
        assertEquals("Cat toc", booking.serviceName());
    }

    @Test
    void nem_loi_khi_trung_khung_gio() {
        CustomerId customer = new CustomerId(UUID.randomUUID());
        Instant start = Instant.now().plus(1, ChronoUnit.DAYS);
        TimeSlot slot = new TimeSlot(start, start.plus(1, ChronoUnit.HOURS));
        Booking existingBooking = Booking.createNew(customer, "Goi dau", slot);

        assertThrows(IllegalStateException.class, () ->
                service.createBooking(customer, "Cat toc", slot, List.of(existingBooking)));
    }
}
