package com.vietanh.booking.domain.model;

import java.time.Instant;

/**
 * Aggregate root. Khong co annotation framework nao o day - day la nguyen tac
 * cot loi cua Hexagonal Architecture: domain khong biet gi ve JPA hay Spring.
 */
public class Booking {

    private final BookingId id;
    private final CustomerId customerId;
    private final String serviceName;
    private final TimeSlot timeSlot;
    private BookingStatus status;
    private final Instant createdAt;

    private Booking(BookingId id, CustomerId customerId, String serviceName,
                     TimeSlot timeSlot, BookingStatus status, Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.serviceName = serviceName;
        this.timeSlot = timeSlot;
        this.status = status;
        this.createdAt = createdAt;
    }

    /** Factory pattern: tao booking moi, luon bat dau o trang thai PENDING. */
    public static Booking createNew(CustomerId customerId, String serviceName, TimeSlot timeSlot) {
        return new Booking(BookingId.newId(), customerId, serviceName, timeSlot, BookingStatus.PENDING, Instant.now());
    }

    /** Factory pattern: dung khi doc lai tu persistence (adapter goi ham nay). */
    public static Booking reconstitute(BookingId id, CustomerId customerId, String serviceName,
                                        TimeSlot timeSlot, BookingStatus status, Instant createdAt) {
        return new Booking(id, customerId, serviceName, timeSlot, status, createdAt);
    }

    public void confirm() {
        if (status != BookingStatus.PENDING) {
            throw new IllegalStateException("Chi co the xac nhan booking dang o trang thai PENDING");
        }
        this.status = BookingStatus.CONFIRMED;
    }

    public BookingId id() { return id; }
    public CustomerId customerId() { return customerId; }
    public String serviceName() { return serviceName; }
    public TimeSlot timeSlot() { return timeSlot; }
    public BookingStatus status() { return status; }
    public Instant createdAt() { return createdAt; }
}
