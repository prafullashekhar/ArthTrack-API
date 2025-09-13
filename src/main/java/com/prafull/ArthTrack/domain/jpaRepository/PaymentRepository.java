package com.prafull.ArthTrack.domain.jpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prafull.ArthTrack.domain.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    // Find payments by user ID
    List<Payment> findByUserId(Long userId);
    
    // Find payments by user ID ordered by payment date descending
    List<Payment> findByUserIdOrderByPaymentDateDesc(Long userId);
    
    // Find payments by user ID and date range
    @Query("SELECT p FROM Payment p WHERE p.userId = :userId AND p.paymentDate BETWEEN :startDate AND :endDate ORDER BY p.paymentDate DESC")
    List<Payment> findByUserIdAndPaymentDateBetween(@Param("userId") Long userId, 
                                                   @Param("startDate") LocalDateTime startDate, 
                                                   @Param("endDate") LocalDateTime endDate);

      // Find payments by comment containing text
    @Query("SELECT p FROM Payment p WHERE p.userId = :userId AND p.comment LIKE %:comment%")
    List<Payment> findByUserIdAndCommentContaining(@Param("userId") Long userId, @Param("comment") String comment);
}
