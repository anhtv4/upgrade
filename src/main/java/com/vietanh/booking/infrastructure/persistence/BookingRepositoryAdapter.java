package com.vietanh.booking.infrastructure.persistence;

import com.vietanh.booking.domain.model.Booking;
import com.vietanh.booking.domain.model.BookingId;
import com.vietanh.booking.domain.model.CustomerId;
import com.vietanh.booking.domain.model.TimeSlot;
import com.vietanh.booking.domain.port.out.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/** Adapter pattern: implement outbound port, map domain model <-> JPA entity. */
@Component
public class BookingRepositoryAdapter implements BookingRepository {

    private final BookingJpaRepository jpaRepository;

    public BookingRepositoryAdapter(BookingJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Booking save(Booking booking) {
        BookingJpaEntity saved = jpaRepository.save(toEntity(booking));
        return toDomain(saved);
    }

    @Override
    public Optional<Booking> findById(BookingId id) {
        return jpaRepository.findById(id.value()).map(this::toDomain);
    }

    @Override
    public List<Booking> findByCustomerId(CustomerId customerId) {
        return jpaRepository.findByCustomerId(customerId.value()).stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public List<Booking> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .toList();
    }

    private BookingJpaEntity toEntity(Booking booking) {
        return new BookingJpaEntity(
                booking.id().value(),
                booking.customerId().value(),
                booking.serviceName(),
                booking.timeSlot().start(),
                booking.timeSlot().end(),
                booking.status(),
                booking.createdAt()
        );
    }

    private Booking toDomain(BookingJpaEntity entity) {
        return Booking.reconstitute(
                new BookingId(entity.getId()),
                new CustomerId(entity.getCustomerId()),
                entity.getServiceName(),
                new TimeSlot(entity.getSlotStart(), entity.getSlotEnd()),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
