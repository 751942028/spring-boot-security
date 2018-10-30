package com.spring.security.controller;

import com.spring.security.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 必须登录后并且用户ROLE_ADMIN权限才可以访问
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @RequestMapping("/info")
    public String info() {

        // 获取登录的用户名
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String name = auth.getName(); //主体名，即登录用户名

        System.out.println(name);



        return "admin_info";
    }


}
