package com.spring.security.service;

import com.spring.security.model.User;

/**
 * User 服务接口
 */
public interface UserService {

    /**
     * 通过用户名查找用户
     * @param userName
     * @return
     */
    User findUserByUsername(String userName);


}
