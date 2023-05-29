package com.wax.service;


import com.wax.dto.UserDto;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/5/28
 * @description 后台管理用户实现
 */
public interface AdminUserService {
    
    
    UserDto loadUserByUsername(String userName);
    
}
