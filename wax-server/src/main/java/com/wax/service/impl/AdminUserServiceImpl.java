package com.wax.service.impl;

import com.wax.dto.UserDto;
import com.wax.service.AdminUserService;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @version 1.0.0
 * @description 后台用户实现类
 */
@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Override
    public UserDto loadUserByUsername(String userName) {
        if (userName.equals("admin")){
            return UserDto.defultUser(userName);
        }
        return null;
    }
}
