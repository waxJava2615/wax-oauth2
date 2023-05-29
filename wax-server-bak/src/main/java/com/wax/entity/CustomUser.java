package com.wax.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/4/25
 * @description 用户表
 */
public class CustomUser extends User {
    public CustomUser(String username, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
    
}
