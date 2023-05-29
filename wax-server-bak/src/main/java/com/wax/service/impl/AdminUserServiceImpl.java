package com.wax.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wax.entity.AdminUser;
import com.wax.service.AdminUserService;
import com.wax.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser>
        implements AdminUserService {
    
    @Autowired
    AdminUserMapper adminUserMapper;
    
    @Override
    public AdminUser loadUserByUsername(String username) {
        
        return adminUserMapper.loadUserByUsername(username);
    }
}
