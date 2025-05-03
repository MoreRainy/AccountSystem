package com.javaproject.record.controller;

import com.javaproject.record.Entity.Category;
import com.javaproject.record.Result.Result;
import com.javaproject.record.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据分类id删除分类
     *
     * @param categoryId
     * @return
     */
    @DeleteMapping("/delete/{categoryId}")
    public Result deleteCategoryById(@PathVariable int categoryId){
        categoryService.deleteCategoryById(categoryId);

        return Result.success();
    }
}
