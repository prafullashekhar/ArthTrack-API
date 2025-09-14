package com.prafull.ArthTrack.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.prafull.ArthTrack.common.EntityMapper;
import com.prafull.ArthTrack.common.StringUtil;
import com.prafull.ArthTrack.common.RowStatus;
import com.prafull.ArthTrack.domain.entity.Category;
import com.prafull.ArthTrack.domain.entity.ExpenseType;
import com.prafull.ArthTrack.domain.entity.Friend;
import com.prafull.ArthTrack.domain.entity.PaymentType;
import com.prafull.ArthTrack.domain.jpaRepository.CategoryRepository;
import com.prafull.ArthTrack.domain.jpaRepository.ExpenseTypeRepository;
import com.prafull.ArthTrack.domain.jpaRepository.FriendRepository;
import com.prafull.ArthTrack.domain.jpaRepository.PaymentTypeRepository;
import com.prafull.ArthTrack.exception.ValidationException;
import com.prafull.ArthTrack.model.detailModel.CategoryDetailModel;
import com.prafull.ArthTrack.model.detailModel.ExpenseDetailModel;
import com.prafull.ArthTrack.model.detailModel.FriendDetailModel;
import com.prafull.ArthTrack.model.detailModel.PaymentTypeDetailModel;
import com.prafull.ArthTrack.security.ThreadLocalUserContext;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateListService {
    private final CategoryRepository categoryRepository;
    private final ExpenseTypeRepository expenseTypeRepository;
    private final FriendRepository friendRepository;
    private final PaymentTypeRepository paymentTypeRepository;
    private final EntityMapper entityMapper;


    // pass the id as null if you want to create a new category for the user.
    @Transactional
    public List<CategoryDetailModel> updateCategoryList(List<CategoryDetailModel> request) {
        Long currentUserId = ThreadLocalUserContext.getCurrentUserId();
        
        for(CategoryDetailModel detail1 : request) {
            Category category;

            if(StringUtil.equals(detail1.getRowStatus(), RowStatus.NEW)) {
                category = new Category();
                category.setName(detail1.getName());
                category.setUserId(currentUserId);

                ExpenseType expenseType = expenseTypeRepository.findById(detail1.getExpenseTypeId())
                    .orElse(null);

                if(expenseType != null) {
                    category.setExpenseType(expenseType);
                }else{
                    log.error("ExpenseType not found with id: {}", detail1.getExpenseTypeId());
                }

                categoryRepository.save(category);

            }else if(StringUtil.equals(detail1.getRowStatus(), RowStatus.MODIFY)) {
                category = categoryRepository.findById(detail1.getId())
                    .filter(c -> c.getUserId().equals(currentUserId))
                    .orElse(null);

                if(category != null) {
                    category.setName(detail1.getName());
                    categoryRepository.save(category);
                }else{
                    log.error("Category not found with id: {}", detail1.getId());
                }

            }else if(StringUtil.equals(detail1.getRowStatus(), RowStatus.DELETE)) {
                category = categoryRepository.findById(detail1.getId())
                    .filter(c -> c.getUserId().equals(currentUserId))
                    .orElse(null);

                if(category != null) {
                    categoryRepository.delete(category);
                }else{
                    log.error("Category not found with id: {}", detail1.getId());
                }
            }
        }
        
        // Return all categories for the current user
        List<Category> allCategories = categoryRepository.findAll();
        List<CategoryDetailModel> categoryDetails = entityMapper.toCategoryDetailModelList(allCategories);

        return categoryDetails;
    }


    public List<ExpenseDetailModel> updateExpenseList(List<ExpenseDetailModel> request) {
        Long currentUserId = ThreadLocalUserContext.getCurrentUserId();

        for(ExpenseDetailModel detail1 : request) {
            ExpenseType expenseType;

            if(StringUtil.equals(detail1.getRowStatus(), RowStatus.NEW)) {
                expenseType = new ExpenseType();
                expenseType.setName(detail1.getName());
                expenseType.setUserId(currentUserId);
                expenseTypeRepository.save(expenseType);

            }else if(StringUtil.equals(detail1.getRowStatus(), RowStatus.MODIFY)) {
                expenseType = expenseTypeRepository.findById(detail1.getId())
                    .orElse(null);

                if(expenseType != null) {
                    expenseType.setName(detail1.getName());
                    expenseTypeRepository.save(expenseType);
                }else{
                    log.error("ExpenseType not found with id: {}", detail1.getId());
                }

            }else if(StringUtil.equals(detail1.getRowStatus(), RowStatus.DELETE)) {
                expenseType = expenseTypeRepository.findById(detail1.getId())
                    .orElse(null);

                if(expenseType != null) {
                    try{
                        expenseTypeRepository.delete(expenseType);
                    }catch(DataIntegrityViolationException e) {
                        throw new ValidationException(
                            "Please delete all categories associated with this expense (" + expenseType.getName() + ") first");
                    }
                    
                }else{
                    log.error("ExpenseType not found with id: {}", detail1.getId());
                }

            }
        }

        List<ExpenseType> allExpenseTypes = expenseTypeRepository.findAll();
        List<ExpenseDetailModel> expenseDetails = entityMapper.toExpenseDetailModelList(allExpenseTypes);

        return expenseDetails;
    }

    public List<FriendDetailModel> updateFriendList(List<FriendDetailModel> request) {
        Long currentUserId = ThreadLocalUserContext.getCurrentUserId();
        
        for(FriendDetailModel detail1 : request) {  
            Friend friend;

            if(StringUtil.equals(detail1.getRowStatus(), RowStatus.NEW)) {
                friend = new Friend();
                friend.setName(detail1.getName());
                friend.setUserId(currentUserId);
                friendRepository.save(friend);

            }else if(StringUtil.equals(detail1.getRowStatus(), RowStatus.MODIFY)) {
                friend = friendRepository.findById(detail1.getId())
                    .orElse(null);

                if(friend != null) {
                    friend.setName(detail1.getName());
                    friendRepository.save(friend);
                }else{
                    log.error("Friend not found with id: {}", detail1.getId());
                }

            }else if(StringUtil.equals(detail1.getRowStatus(), RowStatus.DELETE)) {
                    friend = friendRepository.findById(detail1.getId())
                    .orElse(null);

                if(friend != null) {
                    friendRepository.delete(friend);
                }else{
                    log.error("Friend not found with id: {}", detail1.getId());
                }

            }
        }

        List<Friend> allFriends = friendRepository.findAll();
        List<FriendDetailModel> friendDetails = entityMapper.toFriendDetailModelList(allFriends);
        
        return friendDetails;  
    }


    public List<PaymentTypeDetailModel> updatePaymentTypeList(List<PaymentTypeDetailModel> request) {
        Long currentUserId = ThreadLocalUserContext.getCurrentUserId();
        
        for(PaymentTypeDetailModel detail1 : request) {
            PaymentType paymentType;

            if(StringUtil.equals(detail1.getRowStatus(), RowStatus.NEW)) {
                paymentType = new PaymentType();
                paymentType.setName(detail1.getName());
                paymentType.setUserId(currentUserId);
                paymentTypeRepository.save(paymentType);

            }else if(StringUtil.equals(detail1.getRowStatus(), RowStatus.MODIFY)) {
                paymentType = paymentTypeRepository.findById(detail1.getId())
                    .orElse(null);

                if(paymentType != null) {
                    paymentType.setName(detail1.getName());
                    paymentTypeRepository.save(paymentType);
                }else{
                    log.error("PaymentType not found with id: {}", detail1.getId());
                }

            }else if(StringUtil.equals(detail1.getRowStatus(), RowStatus.DELETE)) {
                paymentType = paymentTypeRepository.findById(detail1.getId())
                    .orElse(null);

                if(paymentType != null) {
                    paymentTypeRepository.delete(paymentType);
                }else{
                    log.error("PaymentType not found with id: {}", detail1.getId());
                }

            }
        }

        List<PaymentType> allPaymentTypes = paymentTypeRepository.findAll();
        List<PaymentTypeDetailModel> paymentTypeDetails = entityMapper.toPaymentTypeDetailModelList(allPaymentTypes);
        
        return paymentTypeDetails;
    }
}
