package com.wax.service.impl;

import com.wax.dto.UserDto;
import com.wax.service.PortalUserService;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @version 1.0.0
 * @description 门户用户
 */

@Service
public class PortalUserServiceImpl implements PortalUserService {
    @Override
    public UserDto loadUserByUsername(String username) {
        if (username.equals("portal")){
            return UserDto.defultUser(username);
        }
        return null;
    }
}
