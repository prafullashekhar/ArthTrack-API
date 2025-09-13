package com.prafull.ArthTrack.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "expense_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long etId;

    @Column(name = "user_id", nullable = false, length = 100)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String name;
}
