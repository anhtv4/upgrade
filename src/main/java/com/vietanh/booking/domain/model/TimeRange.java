package com.vietanh.booking.domain.model;

import java.time.LocalDateTime;

/**
 * Khoảng thời gian nửa mở [timeStart, timeEnd): gồm timeStart, không gồm timeEnd.
 * Chỉ kiểm tra bất biến đúng ở mọi thời điểm. Quy tắc "không đặt quá khứ" nằm ở Booking.createNew.
 */
public record TimeRange(LocalDateTime timeStart, LocalDateTime timeEnd) {

    public TimeRange {
        if (timeStart == null || timeEnd == null) {
            throw new IllegalArgumentException("Start time and end time must not be null");
        }
        if (!timeStart.isBefore(timeEnd)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
    }

    // Không trùng khi timeEnd <= other.timeStart hoặc other.timeEnd <= timeStart -> phủ định lại
    public boolean overlaps(TimeRange other) {
        return timeStart.isBefore(other.timeEnd) && other.timeStart.isBefore(timeEnd);
    }
}