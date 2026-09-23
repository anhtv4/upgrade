package com.vietanh.booking.domain.service;

import com.vietanh.booking.domain.model.Booking;
import com.vietanh.booking.domain.model.TimeSlot;

import java.util.List;

/** Chien luoc mac dinh: tu choi bat ky khung gio nao trung voi booking da co. */
public class NoOverlapConflictPolicy implements ConflictPolicy {

    @Override
    public boolean hasConflict(TimeSlot requested, List<Booking> existingBookings) {
        return existingBookings.stream()
                .anyMatch(b -> b.timeSlot().overlaps(requested));
    }
}
