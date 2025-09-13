package com.prafull.ArthTrack.domain.jpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prafull.ArthTrack.domain.entity.PaymentType;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
    
    // Find payment types by user ID
    List<PaymentType> findByUserId(Long userId);
    
}
