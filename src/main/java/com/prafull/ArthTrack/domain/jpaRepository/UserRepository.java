package com.prafull.ArthTrack.domain.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prafull.ArthTrack.domain.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByUsername(String username);
}
