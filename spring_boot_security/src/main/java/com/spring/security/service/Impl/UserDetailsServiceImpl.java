package com.spring.security.service.Impl;

import com.spring.security.model.Role;
import com.spring.security.model.User;
import com.spring.security.service.RoleService;
import com.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 获取数据库用户信息
        User user = userService.findUserByUsername(username);

        // 获取数据库用户角色
        List<Role> roleList = roleService.findRolesByUsername(username);

        // 将信息转换为UserDetails对象
        return changeToUser(user, roleList);

    }

    private UserDetails changeToUser(User user, List<Role> roleList) {

        // 权限列表
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // 赋予查询到的角色
        for (Role role: roleList) {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            authorityList.add(authority);
        }

        // 创建UserDetails对象，设置用户名，密码和权限
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorityList);
        return userDetails;

    }
}
