package com.wax.controller;

import com.wax.utils.ResultData;
import com.wax.utils.enums.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/5/22
 * @description 认证
 */
@RequestMapping("/oauth")
public class AuthController {
    
    @Autowired
    TokenEndpoint tokenEndpoint;
    
    
    @RequestMapping("/token")
    public ResultData getAccessToken(
            Principal principal,
            Map<String,String> paramMap
    ) throws HttpRequestMethodNotSupportedException {
        ResponseEntity<OAuth2AccessToken> responseEntity =
                tokenEndpoint.postAccessToken(principal, paramMap);
        return ResultData.customizeResult(ResultCode.SUCCESS,responseEntity);
    }
    
}
