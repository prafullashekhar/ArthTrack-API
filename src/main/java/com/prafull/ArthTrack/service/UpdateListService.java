package com.prafull.ArthTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import com.prafull.ArthTrack.common.EntityMapper;
import com.prafull.ArthTrack.domain.entity.Category;
import com.prafull.ArthTrack.domain.entity.ExpenseType;
import com.prafull.ArthTrack.domain.jpaRepository.CategoryRepository;
import com.prafull.ArthTrack.domain.jpaRepository.ExpenseTypeRepository;
import com.prafull.ArthTrack.domain.jpaRepository.FriendRepository;
import com.prafull.ArthTrack.domain.jpaRepository.PaymentTypeRepository;
import com.prafull.ArthTrack.model.detailModel.CategoryDetailModel;
import com.prafull.ArthTrack.model.detailModel.ExpenseDetailModel;
import com.prafull.ArthTrack.model.updateList.CategoryModel;
import com.prafull.ArthTrack.model.updateList.ExpenseModel;
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
    public CategoryModel updateCategoryList(CategoryModel request) {
        Long currentUserId = ThreadLocalUserContext.getCurrentUserId();
        
        for(CategoryDetailModel detail1 : request.getCategories()) {
            Category category;

            if(detail1.getId() != null) {
                // Try to update the existing category
                category = categoryRepository.findById(detail1.getId())
                    .filter(c -> c.getUserId().equals(currentUserId))
                    .orElse(null);

                if(category != null) {
                    category.setName(detail1.getName());
                }else{
                    log.error("Category not found with id: {}", detail1.getId());
                }
            }else {
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
            }

            categoryRepository.save(category);
        }
        
        // Return all categories for the current user
        List<Category> allCategories = categoryRepository.findAll();
        List<CategoryDetailModel> categoryDetails = entityMapper.toCategoryDetailModelList(allCategories);
        
        CategoryModel response = new CategoryModel();
        response.setCategories(categoryDetails);
        return response;
    }


    public ExpenseModel updateExpenseList(ExpenseModel request) {
        Long currentUserId = ThreadLocalUserContext.getCurrentUserId();

        for(ExpenseDetailModel detail1 : request.getExpenses()) {
            ExpenseType expenseType;

            if(detail1.getId() != null) {
                expenseType = expenseTypeRepository.findById(detail1.getId())
                    .orElse(null);

                if(expenseType != null) {
                    expenseType.setName(detail1.getName());
                }else{
                    log.error("ExpenseType not found with id: {}", detail1.getId());
                }
            }else{
                expenseType = new ExpenseType();
                expenseType.setName(detail1.getName());
                expenseType.setUserId(currentUserId);
            }
            
            expenseTypeRepository.save(expenseType);
        }

        List<ExpenseType> allExpenseTypes = expenseTypeRepository.findAll();
        List<ExpenseDetailModel> expenseDetails = entityMapper.toExpenseDetailModelList(allExpenseTypes);
        
        ExpenseModel response = new ExpenseModel();
        response.setExpenses(expenseDetails);
        return response;
    }
}
