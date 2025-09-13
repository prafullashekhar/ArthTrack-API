package com.prafull.ArthTrack.domain.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prafull.ArthTrack.domain.entity.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
    
}
