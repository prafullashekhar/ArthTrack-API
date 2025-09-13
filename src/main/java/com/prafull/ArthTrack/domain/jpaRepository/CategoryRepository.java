package com.prafull.ArthTrack.domain.jpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.prafull.ArthTrack.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // Find categories by user ID
    List<Category> findByUserId(Long userId);
    
    // Find categories by expense type ID
    List<Category> findByExpenseType_etId(Long etId);
    
}
