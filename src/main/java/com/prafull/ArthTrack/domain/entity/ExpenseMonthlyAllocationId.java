package com.prafull.ArthTrack.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseMonthlyAllocationId implements Serializable {

    private Long expenseTypeId;
    private String month;   // YYYYMM

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpenseMonthlyAllocationId)) return false;
        ExpenseMonthlyAllocationId that = (ExpenseMonthlyAllocationId) o;
        return Objects.equals(expenseTypeId, that.expenseTypeId)
                && Objects.equals(month, that.month);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseTypeId, month);
    }
}