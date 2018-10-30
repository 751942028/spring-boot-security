package com.spring.security.service.Impl;

import com.spring.security.model.User;
import com.spring.security.service.UserService;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findUserByUsername(String userName) {

        /**
         * user 数据应该从数据库中查找得到
         * 因没有Dao层，所以模拟了一个假的用户
         */
        User user = new User();
        user.setId(1);
        user.setUserName(userName);
        user.setPassword(
                // 密码编码器；要与WebSecurityConfigure中的密码编码器一样
                new Pbkdf2PasswordEncoder("wx").encode("123456")
        );
        user.setCreatedOn(new Date(System.currentTimeMillis()));
        user.setChangeOn(new Date(System.currentTimeMillis()));

        return user;
    }
}
