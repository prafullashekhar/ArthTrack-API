package com.prafull.ArthTrack.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pId;

    @Column(name = "user_id", nullable = false, length = 100)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_id")
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "et_id")
    private ExpenseType expenseType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_id")
    private Category category;

    @Column(nullable = false)
    private Double amount;   // user’s share

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount; // full payment

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate;

    private String comment;
}
