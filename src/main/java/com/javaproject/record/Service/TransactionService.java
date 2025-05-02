package com.javaproject.record.Service;

import com.javaproject.record.Entity.Transaction;

import java.util.ArrayList;

public interface TransactionService {
    ArrayList<Transaction> getTransactionByUserId(int userId);
}
