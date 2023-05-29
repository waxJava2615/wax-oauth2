package com.wax.utils.enums;

/**
 * @author wax
 * @description: 返回码和信息
 * @date 2022-05
 */
public interface IResultCode {
    
    /**
     * 消息
     *
     * @return
     */
    String getMessage();
    
    
    /**
     * 状态码
     *
     * @return
     */
    int getCode();
    
    
}
