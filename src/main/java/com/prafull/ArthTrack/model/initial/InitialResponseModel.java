package com.prafull.ArthTrack.model.initial;

import java.util.List;

import com.prafull.ArthTrack.model.detailModel.CategoryDetailModel;
import com.prafull.ArthTrack.model.detailModel.ExpenseDetailModel;
import com.prafull.ArthTrack.model.detailModel.FriendDetailModel;
import com.prafull.ArthTrack.model.detailModel.PaymentTypeDetailModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialResponseModel {
    private List<CategoryDetailModel> categories;
    private List<ExpenseDetailModel> expenses;
    private List<FriendDetailModel> friends;
    private List<PaymentTypeDetailModel> paymentTypes;
}
