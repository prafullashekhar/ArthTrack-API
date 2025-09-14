package com.prafull.ArthTrack.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.prafull.ArthTrack.service.UpdateListService;
import com.prafull.ArthTrack.model.detailModel.CategoryDetailModel;
import com.prafull.ArthTrack.model.detailModel.ExpenseDetailModel;
import com.prafull.ArthTrack.model.detailModel.FriendDetailModel;
import com.prafull.ArthTrack.model.detailModel.PaymentTypeDetailModel;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/update-list")
@RequiredArgsConstructor
public class UpdateListController {

    private final UpdateListService updateListService;

    @PostMapping("/category")
    public ResponseEntity<List<CategoryDetailModel>> updateCategoryList(@RequestBody List<CategoryDetailModel> request) {
        return ResponseEntity.ok(updateListService.updateCategoryList(request));
    }

    @PostMapping("/expense")
    public ResponseEntity<List<ExpenseDetailModel>> updateExpenseList(@RequestBody List<ExpenseDetailModel> request) {
        return ResponseEntity.ok(updateListService.updateExpenseList(request));
    }

    @PostMapping("/friend")
    public ResponseEntity<List<FriendDetailModel>> updateFriendList(@RequestBody List<FriendDetailModel> request) {
        return ResponseEntity.ok(updateListService.updateFriendList(request));
    }

    @PostMapping("/payment-type")
    public ResponseEntity<List<PaymentTypeDetailModel>> updatePaymentTypeList(@RequestBody List<PaymentTypeDetailModel> request) {
        return ResponseEntity.ok(updateListService.updatePaymentTypeList(request));
    }
}
