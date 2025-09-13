package com.prafull.ArthTrack.model.detailModel;

import java.math.BigDecimal;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDetailModel {
    private Long id;
    private String name;
    private BigDecimal allocationAmount;
}
