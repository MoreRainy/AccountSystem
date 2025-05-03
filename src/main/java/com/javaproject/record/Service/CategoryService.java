package com.javaproject.record.Service;

import com.javaproject.record.Entity.Category;

import java.util.ArrayList;

public interface CategoryService {
    ArrayList<Category> getCategoryByUserId(int userId);

    void deleteCategoryById(int categoryId);
}
