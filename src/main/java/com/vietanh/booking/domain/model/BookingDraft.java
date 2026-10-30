package com.vietanh.booking.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class BookingDraft {

    private static final Duration FREE_CANCEL_BEFORE = Duration.ofHours(1);

    UUID id;
    UUID customerId;
    TimeRange timeRange;
    BookingStatus status;
    LocalDateTime createdAt;
    LocalDateTime cancelledAt;
    CancellerRole cancelledByRole;
    UUID cancelledById;

    public BookingDraft(UUID id, UUID customerId, TimeRange timeRange) {
        this.id = id;
        this.customerId = customerId;
        this.timeRange = timeRange;
        this.status = BookingStatus.PENDING_PAYMENT;
        this.createdAt = LocalDateTime.now();
    }

    public void cancelByCustomer(LocalDateTime now, UUID cancelCustomerId) {

        if (!Objects.equals(this.customerId, cancelCustomerId)) {
            throw new RuntimeException();
        }
        this.checkCancelTime(now);
        this.checkCancelState();

        if (this.status == BookingStatus.CONFIRMED) {
            Duration duration = Duration.between(now, this.timeRange.timeStart());

            if (duration.compareTo(FREE_CANCEL_BEFORE) >= 0) {
                // hoàn tiền
            }
        }

        this.markDataCancelled(now, cancelCustomerId, CancellerRole.CUSTOMER);
    }

    public void cancelByManager(LocalDateTime now, UUID managerId) {

        this.checkCancelTime(now);
        this.checkCancelState();

        if (this.status == BookingStatus.CONFIRMED) {
                // hoàn tiền
        }

        this.markDataCancelled(now, managerId, CancellerRole.MANAGER);
    }

    public void expire(LocalDateTime now) {

        this.checkCancelState();

        if (this.status == BookingStatus.CONFIRMED) {
            throw new IllegalStateException();
        }

        this.markDataCancelled(now, null, CancellerRole.SYSTEM);
    }

    private void checkCancelState() {
        if (this.status == BookingStatus.CANCELLED || this.status == BookingStatus.COMPLETED) {
            throw new IllegalStateException();
        }
    }

    private void checkCancelTime(LocalDateTime now) {
        if (now.isAfter(this.timeRange.timeStart())) {
            throw new IllegalStateException();
        }
    }

    private void markDataCancelled(LocalDateTime now, UUID cancelledId, CancellerRole cancelledRole) {
        this.status = BookingStatus.CANCELLED;
        this.cancelledAt = now;
        this.cancelledById = cancelledId;
        this.cancelledByRole = cancelledRole;
    }
}
