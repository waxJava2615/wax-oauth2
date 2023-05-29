package com.wax.service;

import com.wax.dto.UserDto;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/5/28
 * @description 门户网站用户实现
 */
public interface PortalUserService {
    
    
    UserDto loadUserByUsername(String userName);
}
