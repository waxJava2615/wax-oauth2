package com.wax.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author wax
 * @version 1.0.0
 */
@Data
@Builder
public class Oauth2TokenDto {
    
    /**
     * 访问令牌
     */
    private String token;
    
    /*
    刷令牌
     */
    private String refreshToken;
    /*
     * 访问令牌头前缀
     */
    private String tokenHead;
    /**
     * 过期时间
     */
    private int expiresIn;
    
}
