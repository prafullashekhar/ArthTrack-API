package com.prafull.ArthTrack.domain.entity;

import java.util.Objects;

import java.io.Serializable;

public class FriendPaymentId implements Serializable {
    private Long friend;
    private Long payment;

    public FriendPaymentId() {}

    public FriendPaymentId(Long friend, Long payment) {
        this.friend = friend;
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendPaymentId)) return false;
        FriendPaymentId that = (FriendPaymentId) o;
        return Objects.equals(friend, that.friend) &&
               Objects.equals(payment, that.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(friend, payment);
    }
}
