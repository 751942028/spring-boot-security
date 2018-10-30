package com.spring.security.service.Impl;

import com.spring.security.model.Role;
import com.spring.security.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findRolesByUsername(String username) {

        // 这里模拟了两个数据库角色ROLE_ADMIN和ROLE_USER

        Role admin = new Role();
        admin.setId(1);
        admin.setRoleName("ROLE_ADMIN");

        Role user = new Role();
        user.setId(2);
        user.setRoleName("ROLE_USER");

        List<Role> roleList = new ArrayList<>();

        if ("admin".equals(username)){
            roleList.add(admin);
        }
        roleList.add(user);
        return roleList;
    }
}
