package com.vietanh.booking.infrastructure.persistence;

import com.vietanh.booking.domain.model.BookingStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

/**
 * Day la "phien ban ha tang" cua Booking - co annotation JPA, tach rieng
 * khoi domain model. Adapter se chiu trach nhiem map qua lai giua 2 ben.
 */
@Entity
@Table(name = "bookings")
public class BookingJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID customerId;

    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false)
    private Instant slotStart;

    @Column(nullable = false)
    private Instant slotEnd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    protected BookingJpaEntity() {
        // required by JPA/Hibernate
    }

    public BookingJpaEntity(UUID id, UUID customerId, String serviceName, Instant slotStart,
                             Instant slotEnd, BookingStatus status, Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.serviceName = serviceName;
        this.slotStart = slotStart;
        this.slotEnd = slotEnd;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public UUID getCustomerId() { return customerId; }
    public String getServiceName() { return serviceName; }
    public Instant getSlotStart() { return slotStart; }
    public Instant getSlotEnd() { return slotEnd; }
    public BookingStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
}
