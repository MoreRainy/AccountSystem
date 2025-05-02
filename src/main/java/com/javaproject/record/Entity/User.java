package com.javaproject.record.Entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
}
