package com.javaproject.record.controller;

import com.javaproject.record.Entity.Category;
import com.javaproject.record.Result.Result;
import com.javaproject.record.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据用户获取分类信息
     *
     * @param userId
     * @return
     */
    @GetMapping
    public Result<ArrayList<Category>> getCategoryByUserId(int userId){
        ArrayList<Category> categoryArrayList = categoryService.getCategoryByUserId(userId);

        return Result.success(categoryArrayList);
    }
}
