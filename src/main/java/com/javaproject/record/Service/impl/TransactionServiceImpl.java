package com.javaproject.record.Service.impl;

import com.javaproject.record.Entity.Transaction;
import com.javaproject.record.Mapper.TransactionMapper;
import com.javaproject.record.Service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;

    /**
     * 根据用户Id查询交易记录
     *
     * @param userId
     * @return
     */
    @Override
    public ArrayList<Transaction> getTransactionByUserId(int userId) {
        ArrayList<Transaction> transactionArrayList = transactionMapper.getTransactionByUserId(userId);

        return transactionArrayList;
    }

    /**
     * 根据记录Id删除交易记录
     *
     * @param transactionId
     */
    @Override
    public void deleteTransactionById(int transactionId) {
        transactionMapper.deleteTransactionById(transactionId);
    }

    /**
     * 新增交易记录
     *
     * @param transaction
     */
    @Override
    public void addTransaction(Transaction transaction) {
        transactionMapper.addTransaction(transaction);
    }

    /**
     * 修改交易记录
     *
     * @param transaction
     */
    @Override
    public void updateTransaction(Transaction transaction) {
        log.info("修改交易记录:{}",transaction);

        transactionMapper.updateTransaction(transaction);
    }
}
