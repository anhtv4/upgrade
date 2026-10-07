package com.vietanh.booking.domain.model;

/**
 * Vòng đời lịch hẹn: PENDING_PAYMENT -> CONFIRMED -> COMPLETED.
 * CANCELLED rẽ nhánh từ PENDING_PAYMENT hoặc CONFIRMED.
 */
public enum BookingStatus {
    // Đang giữ slot, chờ thanh toán (gộp PENDING + AWAITING_PAYMENT, chốt 2026-10-06)
    PENDING_PAYMENT,
    CONFIRMED,
    COMPLETED,
    CANCELLED
}