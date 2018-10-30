package com.spring.security.service;

import com.spring.security.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> findRolesByUsername(String username);
}
