package com.prafull.ArthTrack.model.updateList;

import java.util.List;

import com.prafull.ArthTrack.model.detailModel.PaymentTypeDetailModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTypeModel {
    private List<PaymentTypeDetailModel> paymentTypes;
}
