package com.prafull.ArthTrack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prafull.ArthTrack.common.EntityMapper;
import com.prafull.ArthTrack.domain.entity.Payment;
import com.prafull.ArthTrack.domain.jpaRepository.PaymentRepository;
import com.prafull.ArthTrack.exception.ValidationException;
import com.prafull.ArthTrack.model.detailModel.PaymentDetailModel;
import com.prafull.ArthTrack.model.payment.PaymentRequestModel;
import com.prafull.ArthTrack.model.payment.PaymentSummaryModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchPaymentService {

    private final PaymentRepository paymentRepository;
    private final EntityMapper entityMapper;

    public PaymentDetailModel getPaymentDetail(PaymentRequestModel request) {

        Optional<Payment> paymentOptional = paymentRepository.findByIdCustom(request.getId());
        if(paymentOptional.isEmpty()){
            throw new ValidationException("No such payment exists");
        }
        return entityMapper.toPaymentDetailModel(paymentOptional.get());
    }

    public List<PaymentSummaryModel> getPayments(PaymentRequestModel request) {
        List<Payment> payments = paymentRepository.findAll();
        return entityMapper.toPaymentSummaryModelList(payments);
    }
    
}
