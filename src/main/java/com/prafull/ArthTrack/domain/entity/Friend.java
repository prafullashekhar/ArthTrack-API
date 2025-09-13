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
@Table(name = "friend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friend {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fId;

    @Column(name = "user_id", nullable = false, length = 100)
    private Long userId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Double amount; // your extra use case
}
