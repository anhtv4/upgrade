package com.vietanh.booking.domain.model;

/**
 * Vai trò của người hủy, ghi lại tại thời điểm hủy.
 * F9 dùng để quyết định hoàn tiền, N5 chỉ đếm lịch do SYSTEM hủy (hết hạn giữ slot).
 */
public enum CancellerRole {
    CUSTOMER,
    MANAGER,
    SYSTEM
}