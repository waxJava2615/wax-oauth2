package com.wax.config;

import com.wax.utils.ReqUtils;
import com.wax.utils.SecurityConstants;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/4/25
 * @description 自定义实现
 */
@NoArgsConstructor
public class PreAuthenticatedUserDetailsService<T extends Authentication> implements AuthenticationUserDetailsService<T>, InitializingBean {
    
    
    private Map<String, UserDetailsService> userDetailsServiceMap;
    
    public PreAuthenticatedUserDetailsService(Map<String, UserDetailsService> userDetailsServiceMap) {
        Assert.notNull(userDetailsServiceMap, "userDetailsServiceMap is null");
        this.userDetailsServiceMap = userDetailsServiceMap;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(userDetailsServiceMap, "userDetailsServiceMap must be set");
    }
    
    /**
     * 根据clientID区分登录
     *
     * @param authentication
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserDetails(T authentication) throws UsernameNotFoundException {
        String clientId = ReqUtils.getClientId();
        UserDetailsService userDetailsService = userDetailsServiceMap.get(clientId);
        if (SecurityConstants.ADMIN_CLIENT_ID.equals(clientId)) {
            return userDetailsService.loadUserByUsername(authentication.getName());
        } else {
            return null;
        }
    }
}
