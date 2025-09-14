package com.prafull.ArthTrack.model.detailModel;

import com.prafull.ArthTrack.common.RowStatus;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeDetailModel {
    private Long id;
    private String name;
    private RowStatus rowStatus; 
}
