package com.vietanh.booking.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookingJpaRepository extends JpaRepository<BookingJpaEntity, UUID> {

    List<BookingJpaEntity> findByCustomerId(UUID customerId);
}
