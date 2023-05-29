package com.wax.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wax.entity.AdminUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Entity com.wax.entity.AdminUser
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {
    
    
    AdminUser loadUserByUsername(@Param("username") String username);
    
}
