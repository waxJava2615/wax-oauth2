package com.wax.service;

import com.wax.entity.AdminUser;
import com.wax.entity.AuthAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/4/25
 * @description 用户详情
 */

@Service
public class CustomAdminUserDetailService implements UserDetailsService {
    
    @Autowired
    AdminUserService adminUserService;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("login"));
        AdminUser adminUser = adminUserService.loadUserByUsername(username);
        return new AuthAdminUser(adminUser.getUserName(), adminUser.getPassWord(), list);
    }
}
