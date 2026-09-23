package com.vietanh.booking.domain.service;

import com.vietanh.booking.domain.model.Booking;
import com.vietanh.booking.domain.model.TimeSlot;

import java.util.List;

/**
 * Strategy pattern: cach xu ly xung dot lich co the thay doi (VD: cho phep
 * overbooking co gioi han) ma khong sua BookingDomainService.
 */
public interface ConflictPolicy {

    boolean hasConflict(TimeSlot requested, List<Booking> existingBookings);
}
