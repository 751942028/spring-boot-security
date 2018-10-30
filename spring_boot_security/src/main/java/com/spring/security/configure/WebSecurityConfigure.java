package com.spring.security.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 用来配置用户签名服务，主要是user-details机制，可以给用户赋予角色
     * @param auth 签名管理构造器，用户构建具体的用户 角色
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 密码编码器
        PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder("wx");
        // 使用UserDetails机制对用户进行验证
        auth.userDetailsService(userDetailsService)
        // 设置密码编码器
        .passwordEncoder(passwordEncoder);

    }

    /**
     * 用来配置拦截保护请求，主要是请求的拒绝和放行
     * @param http 安全请求对象
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置请求限制
        http
                // 请求控制相关设置
                // 请求的相关配置采用的是优先匹配原则
                .authorizeRequests()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")

//                .anyRequest().permitAll() // 其它路径运行签名后访问

                .and().authorizeRequests().antMatchers("/welcome").hasAnyAuthority("ROLE_ADMIN")
                .and().anonymous() // 对于没有配置权限的请求，可以匿名访问

                .and()
                // form相关设置
                .csrf().disable() // 禁用csrf保护,如果开启csrf后，需要在form表单中添加csrf隐藏值，并且为post请求
                .formLogin()
                .loginPage("/login/page") // 自定义登录页面
                .defaultSuccessUrl("/welcome") // 登录成功后，欢迎页面
                .and()
                .logout()
                .logoutUrl("/logout/page") // 自定义注销页面
                .logoutSuccessUrl("/login/page") // 注销成功后跳转页面
                .and()
                // http 验证相关设置
                .httpBasic();

    }

    /**
     * 用来配置filter链
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
