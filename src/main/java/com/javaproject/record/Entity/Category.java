package com.javaproject.record.Entity;

import lombok.Data;

@Data
public class Category {
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType; // 1支出 0收入
    private Integer userId;
}
