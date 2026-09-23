package com.vietanh.booking.domain.model;

import java.time.Instant;

public record TimeSlot(Instant start, Instant end) {

    public TimeSlot {
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Thoi gian bat dau phai truoc thoi gian ket thuc");
        }
    }

    public boolean overlaps(TimeSlot other) {
        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }
}
