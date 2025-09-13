package com.prafull.ArthTrack.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.prafull.ArthTrack.service.UpdateListService;
import com.prafull.ArthTrack.model.updateList.CategoryModel;
import com.prafull.ArthTrack.model.updateList.ExpenseModel;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/update-list")
@RequiredArgsConstructor
public class UpdateListController {

    private final UpdateListService updateListService;

    @PostMapping("/category")
    public ResponseEntity<CategoryModel> updateCategoryList(@RequestBody CategoryModel request) {
        return ResponseEntity.ok(updateListService.updateCategoryList(request));
    }

    @PostMapping("/expense")
    public ResponseEntity<ExpenseModel> updateExpenseList(@RequestBody ExpenseModel request) {
        return ResponseEntity.ok(updateListService.updateExpenseList(request));
    }
}
