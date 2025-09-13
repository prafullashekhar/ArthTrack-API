package com.prafull.ArthTrack.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "friend_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FriendPaymentId.class) // composite key
public class FriendPayment {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_id", nullable = false)
    private Friend friend;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_id", nullable = false)
    private Payment payment;

    @Column(nullable = false)
    private Double amount;
}
