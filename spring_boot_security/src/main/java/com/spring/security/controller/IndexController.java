package com.spring.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * 首页可以无条件访问
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Principal principal) {

        if (principal != null) {
            String name3 = principal.getName();
            System.out.println(name3);
        }

        return "index";
    }

    /**
     * 欢迎页面，登录成功后会调到此页面
     * @return
     */
    @RequestMapping("/welcome")

    public String welcome() {
        return "welcome";
    }

}
