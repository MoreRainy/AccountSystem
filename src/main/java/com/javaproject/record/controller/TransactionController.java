package com.javaproject.record.controller;

import com.javaproject.record.Entity.Transaction;
import com.javaproject.record.Result.Result;
import com.javaproject.record.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("user/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     * 根据用户Id查询交易记录
     *
     * @param userId
     * @return
     */
    @GetMapping
    public Result<ArrayList<Transaction>> getTransactionByUserId(int userId){
        ArrayList<Transaction> transactionArrayList =transactionService.getTransactionByUserId(userId);

        return Result.success(transactionArrayList);
    }

}
