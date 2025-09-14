package com.prafull.ArthTrack.model.payment;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSummaryModel {
    private Long id;

    private Long expenseTypeID;

    private Long paymentTypeID;

    private Long categoryID;

    private Double amount;
    private Double totalAmount;
    
    private LocalDateTime paymentDate;
    private String comment;
}
