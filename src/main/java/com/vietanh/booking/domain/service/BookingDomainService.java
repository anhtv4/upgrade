package com.vietanh.booking.domain.service;

import com.vietanh.booking.domain.model.Booking;
import com.vietanh.booking.domain.model.CustomerId;
import com.vietanh.booking.domain.model.TimeSlot;

import java.util.List;

/** Domain service: logic nghiep vu khong thuoc ve 1 entity cu the nao. */
public class BookingDomainService {

    private final ConflictPolicy conflictPolicy;

    public BookingDomainService(ConflictPolicy conflictPolicy) {
        this.conflictPolicy = conflictPolicy;
    }

    public Booking createBooking(CustomerId customerId, String serviceName, TimeSlot timeSlot,
                                  List<Booking> existingBookings) {
        if (conflictPolicy.hasConflict(timeSlot, existingBookings)) {
            throw new IllegalStateException("Khung gio da bi trung voi mot booking khac");
        }
        return Booking.createNew(customerId, serviceName, timeSlot);
    }
}
