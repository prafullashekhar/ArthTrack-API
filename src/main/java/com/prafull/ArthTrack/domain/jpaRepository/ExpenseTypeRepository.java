package com.prafull.ArthTrack.domain.jpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prafull.ArthTrack.domain.entity.ExpenseType;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {
    
    // Find expense types by user ID
    List<ExpenseType> findByUserId(Long userId);

}
