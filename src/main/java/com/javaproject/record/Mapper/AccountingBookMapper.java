package com.javaproject.record.Mapper;

import com.javaproject.record.Entity.AccountBook;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface AccountingBookMapper {
    /**
     * 通过用户id获取所有账本
     *
     * @param userId
     * @return
     */
    @Select("select * from account_book where user_id = #{userId}")
    ArrayList<AccountBook> getAccountBookByUserId(int userId);
}
