package com.vietanh.booking.infrastructure.config;

import com.vietanh.booking.domain.service.BookingDomainService;
import com.vietanh.booking.domain.service.ConflictPolicy;
import com.vietanh.booking.domain.service.NoOverlapConflictPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// domain/ khong duoc @Component hoa vi khong duoc phep phu thuoc Spring.
// Noi day la cho duy nhat "boc" domain object thanh Spring bean.
@Configuration
public class DomainBeanConfig {

    @Bean
    public ConflictPolicy conflictPolicy() {
        return new NoOverlapConflictPolicy();
    }

    @Bean
    public BookingDomainService bookingDomainService(ConflictPolicy conflictPolicy) {
        return new BookingDomainService(conflictPolicy);
    }
}
