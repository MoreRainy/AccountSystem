package com.javaproject.record.Mapper;

import com.javaproject.record.Entity.AccountBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    /**
     * 根据账本Id单次删除
     *
     * @param bookId
     */
    @Delete("delete from account_book where book_id = #{bookId}")
    void deleteAccountBookById(int bookId);

    /**
     * 新增账本
     *
     * @param accountBook
     */
    @Insert("INSERT INTO account_book (user_id, book_name, description) values (#{userId}, #{bookName}, #{description})")
    void addAccountBook(AccountBook accountBook);

    /**
     * 修改账本
     *
     * @param accountBook
     */
    void updateAccountBook(AccountBook accountBook);
}
