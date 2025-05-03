package com.javaproject.record.Service.impl;

import com.javaproject.record.Entity.Category;
import com.javaproject.record.Mapper.CategoryMapper;
import com.javaproject.record.Service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据用户获取分类信息
     *
     * @param userId
     * @return
     */
    @Override
    public ArrayList<Category> getCategoryByUserId(int userId) {
        ArrayList<Category> categoryArrayList = categoryMapper.getCategoryByUserId(userId);

        return categoryArrayList;
    }

    /**
     * 根据分类id删除分类
     *
     * @param categoryId
     */
    @Override
    public void deleteCategoryById(int categoryId) {
        categoryMapper.deleteCategoryById(categoryId);
    }

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @Override
    public void addCategory(Category category) {
        categoryMapper.addCategory(category);
    }

    /**
     * 修改分类
     *
     * @param category
     */
    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }
}
