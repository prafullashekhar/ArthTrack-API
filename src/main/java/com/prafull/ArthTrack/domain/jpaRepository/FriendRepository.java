package com.prafull.ArthTrack.domain.jpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prafull.ArthTrack.domain.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    
    // Find friends by user ID
    List<Friend> findByUserId(Long userId);
    
    // Calculate total amount owed by friends for a user
    @Query("SELECT COALESCE(SUM(f.amount), 0) FROM Friend f WHERE f.userId = :userId")
    Double getTotalAmountByUserId(@Param("userId") Long userId);
}
