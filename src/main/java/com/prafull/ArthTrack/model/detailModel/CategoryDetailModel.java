package com.prafull.ArthTrack.model.detailModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetailModel {
    private Long id;
    private String name;
    private Long expenseTypeId;
}
