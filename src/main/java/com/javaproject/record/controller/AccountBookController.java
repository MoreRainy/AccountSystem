package com.javaproject.record.controller;

import com.javaproject.record.Entity.AccountBook;
import com.javaproject.record.Result.Result;
import com.javaproject.record.Service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user/account-books")
public class AccountBookController {
    @Autowired
    private AccountBookService accountBookService;

    /**
     * 通过用户id获取所有账本
     *
     * @param userId
     * @return
     */
    @GetMapping
    public Result<ArrayList<AccountBook>> getAccountBookByUserId(int userId){
        ArrayList<AccountBook> accountBookArrayList = accountBookService.getAccountBookByUserId(userId);

        return Result.success(accountBookArrayList);
    }
}
