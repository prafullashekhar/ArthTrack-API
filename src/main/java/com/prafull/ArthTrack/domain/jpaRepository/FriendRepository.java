package com.prafull.ArthTrack.domain.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prafull.ArthTrack.domain.entity.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    
}
