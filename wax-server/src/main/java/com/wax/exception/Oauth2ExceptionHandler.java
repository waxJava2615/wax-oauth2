package com.wax.exception;

import com.wax.utils.ResultData;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局处理Oauth2抛出的异常
 */
@RestControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public ResultData handleOauth2(OAuth2Exception e) {
        return ResultData.customizeResult(e.getMessage());
    }
    
    @ExceptionHandler(value = RuntimeException.class)
    public ResultData handle(RuntimeException e) {
        return ResultData.customizeResult(e.getMessage());
    }
}
