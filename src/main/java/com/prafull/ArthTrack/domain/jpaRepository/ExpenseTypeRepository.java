package com.prafull.ArthTrack.domain.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prafull.ArthTrack.domain.entity.ExpenseType;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {

}
