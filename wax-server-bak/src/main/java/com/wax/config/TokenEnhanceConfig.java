package com.wax.config;

import cn.hutool.core.map.MapUtil;
import com.wax.entity.AuthAdminUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Map;

/**
 * JWT Claim 内容增强
 *
 * @author haoxr
 * @date 2022/10/30
 */
@Configuration
@RequiredArgsConstructor
public class TokenEnhanceConfig {
    
    // private final RedisTemplate redisTemplate;
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Object principal = authentication.getUserAuthentication().getPrincipal();
            Map<String, Object> additionalInfo = MapUtil.newHashMap();
            if (principal instanceof AuthAdminUser) {
                AuthAdminUser authAdminUser = (AuthAdminUser) principal;
                additionalInfo.put("userId", authAdminUser.getUsername());
                
                /**
                 * 系统用户按钮权限标识数据量多存放至redis
                 *
                 * key:AUTH:USER_PERMS:2
                 * value:['sys:user:add',...]
                 */
                // redisTemplate.opsForValue().set("AUTH:USER_PERMS:" + sysUserDetails.getUserId(),
//                        sysUserDetails.getPerms());
            
            } /*else if (principal instanceof MemberUserDetails) {
                MemberUserDetails memberUserDetails = (MemberUserDetails) principal;
                additionalInfo.put("memberId", memberUserDetails.getMemberId());
                additionalInfo.put("username", memberUserDetails.getUsername());
            }*/
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
