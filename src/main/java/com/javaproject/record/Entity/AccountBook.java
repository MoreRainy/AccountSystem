package com.javaproject.record.Entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountBook {
    private Integer bookId;
    private Integer userId;
    private String bookName;
    private String description;
    private LocalDateTime createTime;
}
