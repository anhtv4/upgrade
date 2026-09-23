package com.vietanh.booking.domain.model;

// Phase 1: enum don gian de co skeleton chay duoc.
// Phase 2 se nang cap thanh sealed interface + pattern matching de lam State pattern day du
// (moi trang thai tu quyet dinh trang thai ke tiep hop le, thay vi if/else rai rac).
public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED
}
