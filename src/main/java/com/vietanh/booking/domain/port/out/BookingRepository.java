package com.vietanh.booking.domain.port.out;

import com.vietanh.booking.domain.model.Booking;
import com.vietanh.booking.domain.model.BookingId;
import com.vietanh.booking.domain.model.CustomerId;

import java.util.List;
import java.util.Optional;

/** Outbound port (Repository pattern): domain chi biet interface nay, khong biet JPA. */
public interface BookingRepository {

    Booking save(Booking booking);

    Optional<Booking> findById(BookingId id);

    List<Booking> findByCustomerId(CustomerId customerId);

    List<Booking> findAll();
}
