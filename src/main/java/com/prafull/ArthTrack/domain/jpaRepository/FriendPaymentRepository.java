package com.prafull.ArthTrack.domain.jpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prafull.ArthTrack.domain.entity.FriendPayment;
import com.prafull.ArthTrack.domain.entity.FriendPaymentId;

public interface FriendPaymentRepository extends JpaRepository<FriendPayment, FriendPaymentId> {
    
    // Find friend payments by friend ID
    List<FriendPayment> findByFriend_fId(Long friendId);
    
    // Find friend payments by payment ID
    List<FriendPayment> findByPayment_pId(Long paymentId);
    
    // Calculate total amount for a specific friend
    @Query("SELECT COALESCE(SUM(fp.amount), 0) FROM FriendPayment fp WHERE fp.friend.fId = :friendId")
    Double getTotalAmountByFriendId(@Param("friendId") Long friendId);
}
