package com.vietanh.booking.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Aggregate lịch hẹn. Mọi thay đổi trạng thái đi qua method của class này,
 * bên ngoài không set thẳng status.
 */
public class Booking {

    private final UUID id;
    private final UUID customerId;
    private final TimeRange timeRange;
    private final LocalDateTime createdAt;
    private BookingStatus status;
    // Chỉ có giá trị khi status = CANCELLED. cancelledById = null khi SYSTEM hủy
    private CancellerRole cancelledByRole;
    private UUID cancelledById;

    private Booking(UUID id, UUID customerId, TimeRange timeRange, LocalDateTime createdAt,
                    BookingStatus status, CancellerRole cancelledByRole, UUID cancelledById) {
        this.id = id;
        this.customerId = customerId;
        this.timeRange = timeRange;
        this.createdAt = createdAt;
        this.status = status;
        this.cancelledByRole = cancelledByRole;
        this.cancelledById = cancelledById;
    }

    /**
     * Tạo lịch mới. now do tầng application truyền vào (giờ server), không lấy từ client.
     */
    public static Booking createNew(UUID customerId, TimeRange timeRange, LocalDateTime now) {
        if (customerId == null || timeRange == null || now == null) {
            throw new IllegalArgumentException("Customer id, time range and current time must not be null");
        }
        if (!timeRange.timeStart().isAfter(now)) {
            throw new IllegalArgumentException("Booking start time must be in the future");
        }
        return new Booking(UUID.randomUUID(), customerId, timeRange, now,
                BookingStatus.PENDING_PAYMENT, null, null);
    }

    /**
     * Chỉ dùng trong adapter persistence khi đọc từ DB. Không kiểm tra quá khứ vì lịch cũ là hợp lệ.
     */
    public static Booking reconstitute(UUID id, UUID customerId, TimeRange timeRange, LocalDateTime createdAt,
                                       BookingStatus status, CancellerRole cancelledByRole, UUID cancelledById) {
        return new Booking(id, customerId, timeRange, createdAt, status, cancelledByRole, cancelledById);
    }

    public void markPaymentReceived() {
        if (status != BookingStatus.PENDING_PAYMENT) {
            throw new IllegalStateException("Only a pending payment booking can receive payment, current status: " + status);
        }
        status = BookingStatus.CONFIRMED;
    }

    public void complete() {
        if (status != BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Only a confirmed booking can be completed, current status: " + status);
        }
        status = BookingStatus.COMPLETED;
    }

    public void cancel(CancellerRole role, UUID cancelledById) {
        if (role == null) {
            throw new IllegalArgumentException("Canceller role must not be null");
        }
        // SYSTEM hủy = hết hạn giữ slot, không có người thao tác. Các vai trò khác bắt buộc có ID
        if ((role == CancellerRole.SYSTEM) != (cancelledById == null)) {
            throw new IllegalArgumentException("Canceller id must be null only when role is SYSTEM");
        }
        if (status != BookingStatus.PENDING_PAYMENT && status != BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Booking cannot be cancelled, current status: " + status);
        }
        // Hết hạn giữ slot chỉ xảy ra khi chưa thanh toán
        if (role == CancellerRole.SYSTEM && status != BookingStatus.PENDING_PAYMENT) {
            throw new IllegalStateException("System can only cancel a pending payment booking");
        }
        status = BookingStatus.CANCELLED;
        cancelledByRole = role;
        this.cancelledById = cancelledById;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public TimeRange getTimeRange() {
        return timeRange;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public CancellerRole getCancelledByRole() {
        return cancelledByRole;
    }

    public UUID getCancelledById() {
        return cancelledById;
    }

    // Entity so sánh theo ID, không theo giá trị field (khác value object như TimeRange)
    @Override
    public boolean equals(Object o) {
        return o instanceof Booking other && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}