package com.javaproject.record.controller;

import com.javaproject.record.Entity.User;
import com.javaproject.record.Mapper.UserMapper;
import com.javaproject.record.Result.Result;
import com.javaproject.record.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param param
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody User param) {
        log.info("用户登录:{}", param);

        // 直接查询用户名和密码匹配的用户
        User user = userService.userLogin(param);

        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error( "登录失败，用户名或密码错误");
        }
    }
}
