package com.javaproject.record.Mapper;

import com.javaproject.record.Entity.Transaction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface TransactionMapper {

    /**
     * 根据用户Id查询交易记录
     *
     * @param userId
     * @return
     */
    @Select("select * from transaction where user_id = #{userId}")
    ArrayList<Transaction> getTransactionByUserId(int userId);

    /**
     * 根据记录Id删除交易记录
     *
     * @param transactionId
     */
    @Delete("delete from transaction where transaction_id = #{transactionId}")
    void deleteTransactionById(int transactionId);
}
