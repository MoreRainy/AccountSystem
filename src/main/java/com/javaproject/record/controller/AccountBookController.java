package com.javaproject.record.controller;

import com.javaproject.record.Entity.AccountBook;
import com.javaproject.record.Result.Result;
import com.javaproject.record.Service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据账本Id单次删除
     *
     * @param bookId
     * @return
     */
    @DeleteMapping("/delete/{bookId}")
    public Result deleteAccountBookById(@PathVariable("bookId") int bookId){
        accountBookService.deleteAccountBookById(bookId);

        return Result.success();
    }

    /**
     * 新增账本
     *
     * @param accountBook
     * @return
     */
    @PostMapping("/add")
    public Result addAccountBook(@RequestBody AccountBook accountBook){
        accountBookService.addAccountBook(accountBook);

        return Result.success();
    }

    /**
     * 修改账本
     *
     * @param accountBook
     * @return
     */
    @PutMapping("/update")
    public Result updateAccountBook(@RequestBody AccountBook accountBook){
        accountBookService.updateAccountBook(accountBook);

        return Result.success();
    }
}
