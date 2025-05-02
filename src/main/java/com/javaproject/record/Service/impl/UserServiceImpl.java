package com.javaproject.record.Service.impl;

import com.javaproject.record.Entity.User;
import com.javaproject.record.Mapper.UserMapper;
import com.javaproject.record.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param param
     * @return
     */
    @Override
    public User userLogin(User param) {
        User user = userMapper.login(param.getUsername(), param.getPassword());

        return user;
    }
}
