package com.javaproject.record.Service.impl;

import com.javaproject.record.Entity.AccountBook;
import com.javaproject.record.Mapper.AccountingBookMapper;
import com.javaproject.record.Service.AccountBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class AccountBookServiceImpl implements AccountBookService {
    @Autowired
    private AccountingBookMapper accountingBookMapper;

    /**
     * 通过用户id获取所有账本
     *
     * @param userId
     * @return
     */
    @Override
    public ArrayList<AccountBook> getAccountBookByUserId(int userId) {
        ArrayList<AccountBook> accountBookArrayList = accountingBookMapper.getAccountBookByUserId(userId);

        return accountBookArrayList;
    }

    /**
     * 根据账本Id单次删除
     *
     * @param bookId
     * @return
     */
    @Override
    public void deleteAccountBookById(int bookId) {
        accountingBookMapper.deleteAccountBookById(bookId);
    }

    /**
     * 新增账本
     *
     * @param accountBook
     */
    @Override
    public void addAccountBook(AccountBook accountBook) {
        accountingBookMapper.addAccountBook(accountBook);
    }
}
