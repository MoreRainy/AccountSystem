package com.javaproject.record.Mapper;

import com.javaproject.record.Entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface CategoryMapper {
    /**
     * 根据用户获取分类信息
     *
     * @param userId
     * @return
     */
    @Select("select * from category where user_id = #{userId}")
    ArrayList<Category> getCategoryByUserId(int userId);

    /**
     * 根据分类id删除分类
     *
     * @param categoryId
     */
    @Delete("delete from category where category_id = #{categoryId}")
    void deleteCategoryById(int categoryId);

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @Insert("insert into category (category_name, category_type, user_id) values (#{categoryName}, #{categoryType}, #{userId})")
    void addCategory(Category category);
}
