package com.wax.utils;//package com.starry.sky.infrastructure.utils;
//
//import lombok.Getter;
//
///**
// * @author wax
// * @description: 相应码枚举映射
// */
//@Getter
//public enum ResultCode {
//
//
//    SUCCESS(0,"成功"),
//    FAIL(10000,"失败"),
//
//    REQUEST_URL_NOT_FOUND(10400,"资源不存在"),
//    REQUEST_SERVER_ERROR(10500,"网络异常,请稍后再试"),
//    ACCESS_DENIED_NO_PERMISSION(10800,"您没有访问权限"),
//
//    AUTHENTICATION_SUPPORT_POST(20000,"只支持POST提交"),
//    AUTHENTICATION_NOT_USER(20100,"用户不存在"),
//    AUTHENTICATION_ERROR_PASSWORD(20150,"用户密码错误"),
//    AUTHENTICATION_IDENTITY_FAIL(20250,"身份认证失败"),
//    AUTHENTICATION_STATUS_FAIL(20255,"账号被锁定或者注销"),
//    AUTHENTICATION_LOGIN_FAIL(20300,"登录失败,请稍后再试"),
//    AUTHENTICATION_UNAUTHORIZED(20400,"未授权,无法访问"),
//    TOKEN_EXPIRED_OR_INVALID(30000,"token过期或无效"),
//    TOKEN_NOT_NUll(30100,"token不能为空"),
//
//    OAUTH_CLIENT_ID_INVALID(40100,"无效的客户端ID"),
//
//
//
//    INVALID_DATA_ERROR(50000,"无效的数据,请刷新页面在操作"),
//
//
//
//
//    fack(-9999,"");
//
//    private Integer code;
//    private String message;
//
//    ResultCode(Integer code, String message){
//        this.code = code;
//        this.message = message;
//    }
//
//    /**
//     * 根据code获取message
//     *
//     * @param code
//     * @return
//     */
//    public static String getMessageByCode(Integer code) {
//        for (ResultCode ele : values()) {
//            if (ele.getCode().equals(code)) {
//                return ele.getMessage();
//            }
//        }
//        return null;
//    }
//}
