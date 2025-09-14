package com.prafull.ArthTrack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.prafull.ArthTrack.model.detailModel.PaymentDetailModel;
import com.prafull.ArthTrack.model.payment.PaymentRequestModel;
import com.prafull.ArthTrack.model.payment.PaymentSummaryModel;
import com.prafull.ArthTrack.service.CreatePaymentService;
import com.prafull.ArthTrack.service.FetchPaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final CreatePaymentService createPaymentService;
    private final FetchPaymentService fetchPaymentService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createPayment(@RequestBody PaymentDetailModel request) {
        return ResponseEntity.ok(createPaymentService.createPayment(request));
    }

    @PostMapping("/get-detail")
    public ResponseEntity<PaymentDetailModel> getPaymentDetail(@RequestBody PaymentRequestModel request) {
        return ResponseEntity.ok(fetchPaymentService.getPaymentDetail(request));
    }

    @PostMapping("/get")
    public ResponseEntity<List<PaymentSummaryModel>> getPayments(@RequestBody PaymentRequestModel request) {
        return ResponseEntity.ok(fetchPaymentService.getPayments(request));
    }
    
}
