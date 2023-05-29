package com.wax.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wax.entity.AdminUser;

/**
 *
 */
public interface AdminUserService extends IService<AdminUser> {
    
    
    AdminUser loadUserByUsername(String username);
}
