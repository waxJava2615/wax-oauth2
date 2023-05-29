package com.wax.service.impl;

import com.wax.constants.AuthConstant;
import com.wax.constants.MessageConstant;
import com.wax.dto.UserDto;
import com.wax.entity.User;
import com.wax.service.AdminUserService;
import com.wax.service.PortalUserService;
import com.wax.utils.ReqUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/5/28
 * @description userdetail  实现类
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    
    @Autowired
    AdminUserService adminUserService;
    
    @Autowired
    PortalUserService portalUserService;
    
    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        /**
         * 此处暂时 的USER 应该是一个DTO 调用远程服务获取得到
         */
        UserDto userDto = null;
        String clientId = ReqUtils.getClientId();
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)){
            userDto = adminUserService.loadUserByUsername(username);
        }else {
            userDto = portalUserService.loadUserByUsername(username);
        }
        if (userDto==null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        User securityUser = new User(userDto);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(MessageConstant.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(MessageConstant.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(MessageConstant.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(MessageConstant.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }
}
