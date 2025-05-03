package com.javaproject.record.controller;

import com.javaproject.record.Entity.Transaction;
import com.javaproject.record.Result.Result;
import com.javaproject.record.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据记录Id删除交易记录
     *
     * @param transactionId
     * @return
     */
    @DeleteMapping("/delete/{transactionId}")
    public Result deleteTransactionById(@PathVariable int transactionId){
        transactionService.deleteTransactionById(transactionId);

        return Result.success();
    }

    /**
     * 新增交易记录
     *
     * @param transaction
     * @return
     */
    @PostMapping("/add")
    public Result addTransaction(@RequestBody Transaction transaction){
        transactionService.addTransaction(transaction);

        return Result.success();
    }

    /**
     * 修改交易记录
     *
     * @param transaction
     * @return
     */
    @PutMapping("/update")
    public Result updateTransaction(@RequestBody Transaction transaction){
        transactionService.updateTransaction(transaction);

        return Result.success();
    }
}
