package com.prafull.ArthTrack.model.detailModel;

import java.time.LocalDateTime;
import java.util.List;

import com.prafull.ArthTrack.common.RowStatus;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailModel {
    private Long id;

    private Long expenseTypeID;
    private String expenseTypeName;

    private Long paymentTypeID;
    private String paymentTypeName;

    private Long categoryID;
    private String categoryName;

    private Double amount;
    private Double totalAmount;

    private List<FriendDetailModel> friends;
    
    private LocalDateTime paymentDate;
    private String comment;
}
