package com.prafull.ArthTrack.domain.entity;

import com.prafull.ArthTrack.common.UserScopedEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MapsId;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "expense_monthly_allocation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseMonthlyAllocation extends UserScopedEntity {

    @EmbeddedId
    private ExpenseMonthlyAllocationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("expenseTypeId") // maps composite key field to relation
    @JoinColumn(name = "et_id", nullable = false)
    private ExpenseType expenseType;

    @Column(name = "allocation_amount", nullable = false)
    private Double allocationAmount;
}