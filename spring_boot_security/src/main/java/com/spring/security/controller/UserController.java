package com.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 必须登录后才可以访问
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/info")
    public String info() {

        return "user_info";
    }
}
