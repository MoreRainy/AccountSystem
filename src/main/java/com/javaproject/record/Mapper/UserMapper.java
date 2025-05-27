package com.javaproject.record.Mapper;

import com.javaproject.record.Entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);
}
