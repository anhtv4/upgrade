package com.vietanh.booking.infrastructure.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record CreateBookingRequest(
        @NotNull UUID customerId,
        @NotBlank String serviceName,
        @NotNull @Future Instant start,
        @NotNull @Future Instant end
) {
}
