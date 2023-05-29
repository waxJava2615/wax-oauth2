//package com.wax.exception;
//
//import com.wax.utils.ResultData;
//import com.wax.utils.enums.ResultCode;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * @author wax
// * @version 1.0.0
// * @date 2023/5/22
// * @description 认证异常
// */
//@Slf4j
//@RestControllerAdvice
//public class AuthException {
//
//
//    @ExceptionHandler({RuntimeException.class})
//    public ResultData fail(Exception e){
//        log.error("异常",e);
//        return ResultData.failResult();
//    }
//
//    /**
//     * 用户不存在
//     *
//     * @param e
//     * @return
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResultData handleUsernameNotFoundException(UsernameNotFoundException e) {
//        return ResultData.customizeResult(ResultCode.USER_NOT_FOUND);
//    }
//
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResultData handleBadCredentialsException(BadCredentialsException e) {
//        return ResultData.customizeResult(ResultCode.USERNAME_OR_PASSWORD_ERROR);
//    }
//
//    /**
//     * 用户名和密码异常
//     *
//     * @param e
//     * @return
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(InvalidGrantException.class)
//    public ResultData handleInvalidGrantException(InvalidGrantException e) {
//        return ResultData.customizeResult(ResultCode.USERNAME_OR_PASSWORD_ERROR);
//    }
//
//
//    /**
//     * 账户异常(禁用、锁定、过期)
//     *
//     * @param e
//     * @return
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({InternalAuthenticationServiceException.class})
//    public ResultData handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
//        return ResultData.customizeResult(e.getMessage());
//    }
//
//    /**
//     * token 无效或已过期
//     *
//     * @param e
//     * @return
//     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler({InvalidTokenException.class})
//    public ResultData handleInvalidTokenExceptionException(InvalidTokenException e) {
//        return ResultData.customizeResult(e.getMessage());
//    }
//}
