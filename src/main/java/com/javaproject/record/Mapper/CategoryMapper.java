package com.javaproject.record.Mapper;

import com.javaproject.record.Entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
