package com.javaproject.record.Service;

import com.javaproject.record.Entity.User;

public interface UserService {
    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User userLogin(User user);
}
