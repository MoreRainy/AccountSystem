package com.javaproject.record.Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private Integer transactionId;
    private Integer userId;
    private Integer bookId;
    private Integer categoryId;
    private Integer amount;
    private LocalDateTime transactionTime;
    private String note;
}
