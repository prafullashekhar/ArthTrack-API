package com.prafull.ArthTrack.common;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.prafull.ArthTrack.domain.entity.Category;
import com.prafull.ArthTrack.domain.entity.ExpenseType;
import com.prafull.ArthTrack.domain.entity.Friend;
import com.prafull.ArthTrack.domain.entity.PaymentType;
import com.prafull.ArthTrack.model.detailModel.CategoryDetailModel;
import com.prafull.ArthTrack.model.detailModel.ExpenseDetailModel;
import com.prafull.ArthTrack.model.detailModel.FriendDetailModel;
import com.prafull.ArthTrack.model.detailModel.PaymentTypeDetailModel;

@Component
public class EntityMapper {

    // Category Mappings
    public CategoryDetailModel toCategoryDetailModel(Category category) {
        if (category == null) return null;
        
        CategoryDetailModel model = new CategoryDetailModel();
        model.setId(category.getCId());
        model.setName(category.getName());
        if (category.getExpenseType() != null) {
            model.setExpenseTypeId(category.getExpenseType().getEtId());
        }
        model.setRowStatus(RowStatus.NO_CHANGE);
        return model;
    }

    public List<CategoryDetailModel> toCategoryDetailModelList(List<Category> categories) {
        if (categories == null) return null;
        
        return categories.stream()
            .map(this::toCategoryDetailModel)
            .collect(Collectors.toList());
    }

    public Category toCategory(CategoryDetailModel model, Long userId) {
        if (model == null) return null;
        
        Category category = new Category();
        category.setCId(model.getId());
        category.setName(model.getName());
        category.setUserId(userId);
        return category;
    }

    // ExpenseType Mappings
    public ExpenseDetailModel toExpenseDetailModel(ExpenseType expenseType) {
        if (expenseType == null) return null;
        
        ExpenseDetailModel model = new ExpenseDetailModel();
        model.setId(expenseType.getEtId());
        model.setName(expenseType.getName());
        model.setRowStatus(RowStatus.NO_CHANGE);
        return model;
    }

    public List<ExpenseDetailModel> toExpenseDetailModelList(List<ExpenseType> expenseTypes) {
        if (expenseTypes == null) return null;
        
        return expenseTypes.stream()
            .map(this::toExpenseDetailModel)
            .collect(Collectors.toList());
    }

    public ExpenseType toExpenseType(ExpenseDetailModel model, Long userId) {
        if (model == null) return null;
        
        ExpenseType expenseType = new ExpenseType();
        expenseType.setEtId(model.getId());
        expenseType.setName(model.getName());
        expenseType.setUserId(userId);
        return expenseType;
    }

    // Friend Mappings
    public FriendDetailModel toFriendDetailModel(Friend friend) {
        if (friend == null) return null;
        
        FriendDetailModel model = new FriendDetailModel();
        model.setId(friend.getFId());
        model.setName(friend.getName());
        model.setRowStatus(RowStatus.NO_CHANGE);
        return model;
    }

    public List<FriendDetailModel> toFriendDetailModelList(List<Friend> friends) {
        if (friends == null) return null;
        
        return friends.stream()
            .map(this::toFriendDetailModel)
            .collect(Collectors.toList());
    }

    public Friend toFriend(FriendDetailModel model, Long userId) {
        if (model == null) return null;
        
        Friend friend = new Friend();
        friend.setFId(model.getId());
        friend.setName(model.getName());
        friend.setAmount(null);
        friend.setUserId(userId);
        return friend;
    }

    // PaymentType Mappings
    public PaymentTypeDetailModel toPaymentTypeDetailModel(PaymentType paymentType) {
        if (paymentType == null) return null;
        
        PaymentTypeDetailModel model = new PaymentTypeDetailModel();
        model.setId(paymentType.getPtId());
        model.setName(paymentType.getName());
        model.setRowStatus(RowStatus.NO_CHANGE);
        return model;
    }

    public List<PaymentTypeDetailModel> toPaymentTypeDetailModelList(List<PaymentType> paymentTypes) {
        if (paymentTypes == null) return null;
        
        return paymentTypes.stream()
            .map(this::toPaymentTypeDetailModel)
            .collect(Collectors.toList());
    }

    public PaymentType toPaymentType(PaymentTypeDetailModel model, Long userId) {
        if (model == null) return null;
        
        PaymentType paymentType = new PaymentType();
        paymentType.setPtId(model.getId());
        paymentType.setName(model.getName());
        paymentType.setUserId(userId);
        return paymentType;
    }
}
