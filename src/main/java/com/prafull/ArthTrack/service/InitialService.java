package com.prafull.ArthTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prafull.ArthTrack.common.EntityMapper;
import com.prafull.ArthTrack.domain.entity.Category;
import com.prafull.ArthTrack.domain.entity.Friend;
import com.prafull.ArthTrack.domain.entity.PaymentType;
import com.prafull.ArthTrack.domain.entity.ExpenseType;
import com.prafull.ArthTrack.domain.jpaRepository.CategoryRepository;
import com.prafull.ArthTrack.domain.jpaRepository.ExpenseTypeRepository;
import com.prafull.ArthTrack.domain.jpaRepository.FriendRepository;
import com.prafull.ArthTrack.domain.jpaRepository.PaymentTypeRepository;
import com.prafull.ArthTrack.model.detailModel.CategoryDetailModel;
import com.prafull.ArthTrack.model.detailModel.ExpenseDetailModel;
import com.prafull.ArthTrack.model.detailModel.FriendDetailModel;
import com.prafull.ArthTrack.model.detailModel.PaymentTypeDetailModel;
import com.prafull.ArthTrack.model.initial.InitialResponseModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InitialService {

    private final CategoryRepository categoryRepository;
    private final ExpenseTypeRepository expenseTypeRepository;
    private final FriendRepository friendRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final EntityMapper entityMapper;


    public InitialResponseModel getInitialData() {
        List<Category> categories = categoryRepository.findAll();
        List<ExpenseType> expenses = expenseTypeRepository.findAll();
        List<Friend> friends = friendRepository.findAll();
        List<PaymentType> paymentTypes = paymentTypeRepository.findAll();

        List<CategoryDetailModel> categoryDetailModels = entityMapper.toCategoryDetailModelList(categories);
        List<ExpenseDetailModel> expenseDetailModels = entityMapper.toExpenseDetailModelList(expenses);
        List<FriendDetailModel> friendDetailModels = entityMapper.toFriendDetailModelList(friends);
        List<PaymentTypeDetailModel> paymentTypeDetailModels = entityMapper.toPaymentTypeDetailModelList(paymentTypes);

        InitialResponseModel initialResponseModel = new InitialResponseModel();
        initialResponseModel.setCategories(categoryDetailModels);
        initialResponseModel.setExpenses(expenseDetailModels);
        initialResponseModel.setFriends(friendDetailModels);
        initialResponseModel.setPaymentTypes(paymentTypeDetailModels);
        return initialResponseModel;
    }
}
