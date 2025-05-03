package com.javaproject.record.Service;

import com.javaproject.record.Entity.Transaction;

import java.util.ArrayList;

public interface TransactionService {
    ArrayList<Transaction> getTransactionByUserId(int userId);

    void deleteTransactionById(int transactionId);

    void addTransaction(Transaction transaction);

    void updateTransaction(Transaction transaction);
}
