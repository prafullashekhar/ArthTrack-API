package com.prafull.ArthTrack.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.prafull.ArthTrack.domain.jpaRepository.FriendPaymentRepository;
import com.prafull.ArthTrack.domain.jpaRepository.FriendRepository;
import com.prafull.ArthTrack.domain.jpaRepository.ExpenseTypeRepository;
import com.prafull.ArthTrack.domain.jpaRepository.CategoryRepository;
import com.prafull.ArthTrack.domain.jpaRepository.PaymentRepository;
import com.prafull.ArthTrack.domain.jpaRepository.PaymentTypeRepository;
import com.prafull.ArthTrack.exception.ValidationException;
import com.prafull.ArthTrack.domain.entity.Payment;
import com.prafull.ArthTrack.domain.entity.PaymentType;
import com.prafull.ArthTrack.domain.entity.ExpenseType;
import com.prafull.ArthTrack.domain.entity.Friend;
import com.prafull.ArthTrack.domain.entity.FriendPayment;
import com.prafull.ArthTrack.common.EntityMapper;
import com.prafull.ArthTrack.domain.entity.Category;
import com.prafull.ArthTrack.model.detailModel.FriendDetailModel;
import com.prafull.ArthTrack.model.detailModel.PaymentDetailModel;
import com.prafull.ArthTrack.security.ThreadLocalUserContext;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final ExpenseTypeRepository expenseTypeRepository;
    private final CategoryRepository categoryRepository;
    private final FriendPaymentRepository friendPaymentRepository;
    private final FriendRepository friendRepository;
    private final EntityMapper entityMapper;


    @Transactional
    public PaymentDetailModel createPayment(PaymentDetailModel request) {
        Long currentUserId = ThreadLocalUserContext.getCurrentUserId();

        // If all the required fields are not present, throw an exception
        validatePayment(request);

        Optional<PaymentType> paymentType = paymentTypeRepository.findById(request.getPaymentTypeID());
        Optional<ExpenseType> expenseType = expenseTypeRepository.findById(request.getExpenseTypeID());
        Optional<Category> category = categoryRepository.findById(request.getCategoryID());

        // If all data is not present, throw an exception
        validateDataPresentIsCrorect(paymentType, expenseType, category);

        Payment payment = new Payment();
        payment.setUserId(currentUserId);
        payment.setPaymentType(paymentType.get());
        payment.setExpenseType(expenseType.get());
        payment.setCategory(category.get());
        payment.setPaymentDate(request.getPaymentDate());

        payment.setTotalAmount(request.getTotalAmount());

        // calulate the amount for user share
        if(request.getFriends() != null && request.getFriends().size() > 0)
            payment.setAmount(calculateUserShare(request));
        else
            payment.setAmount(request.getTotalAmount());

        payment.setComment(request.getComment());

        Payment pay = paymentRepository.saveAndFlush(payment);

        if(request.getFriends() != null && request.getFriends().size() > 0){
            saveExpenseForAllFriends(request, pay);
        }

        return entityMapper.toPaymentDetailModel(pay);
    }

    @Transactional
    private void saveExpenseForAllFriends(PaymentDetailModel request, Payment payment){
        List<FriendPayment> lst = new ArrayList<>();
        List<Friend> frLst = new ArrayList<>();

        for(FriendDetailModel model : request.getFriends()){
            Friend friend = friendRepository.findById(model.getId()).get();

            lst.add(new FriendPayment(friend, payment, model.getAmount()));
            friend.setAmount(friend.getAmount() + model.getAmount());
            frLst.add(friend);
        }

        friendPaymentRepository.saveAll(lst);
        friendRepository.saveAll(frLst);
    }


    private Double calculateUserShare(PaymentDetailModel request) {
        Double usersShare = request.getTotalAmount();

        for(FriendDetailModel friend : request.getFriends()) {
            Optional<Friend> friendOptional = friendRepository.findById(request.getId());
            if(friendOptional.isEmpty()){
                throw new ValidationException("Friend "+ friend.getName() + " - Not present");
            }
            usersShare -= friend.getAmount();
        }
        return usersShare;
    }

    private void validateDataPresentIsCrorect(Optional<PaymentType> paymentType, Optional<ExpenseType> expenseType,
            Optional<Category> category) {

        if(!paymentType.isPresent()) {
            throw new ValidationException("Payment Type - Not Correct");
        }

        if(!expenseType.isPresent()) {
            throw new ValidationException("Expense Type - Not Correct");
        }

        if(!category.isPresent()) {
            throw new ValidationException("Category - Not Correct");
        }
        
    }

    private void validatePayment(PaymentDetailModel request) {

        if(request.getTotalAmount() == null || request.getTotalAmount() <= 0) {
            throw new ValidationException("Total Amount - Required");
        }

        if(request.getPaymentDate() == null) {
            request.setPaymentDate(LocalDateTime.now());
        }

        if(request.getPaymentDate().isAfter(LocalDateTime.now())) {
            throw new ValidationException("Payment Date - Cannot be in the future");
        }

        if(request.getExpenseTypeID() == null) {
            throw new ValidationException("Expense Type - Required");
        }

        if(request.getCategoryID() == null) {
            throw new ValidationException("Category - Required");
        }

        if(request.getPaymentTypeID() == null) {
            throw new ValidationException("Payment Type - Required");
        }
    }
    
}
