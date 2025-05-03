package com.javaproject.record.Service;

import com.javaproject.record.Entity.AccountBook;

import java.util.ArrayList;

public interface AccountBookService {
    ArrayList<AccountBook> getAccountBookByUserId(int userId);

    void deleteAccountBookById(int bookId);
}
