package com.wax.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.wax.utils.enums.IResultCode;
import com.wax.utils.enums.ResultCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wax
 * @description: 请求相应类
 * @date 2021-08-20
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
public class ResultData<T> extends ResultBase {
    
    
    public static final String DATA_FIELD = "data";
    
    private T data;
    
    
    public static ResultData customizeResult(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.FAIL.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    
    public static ResultData customizeResult(IResultCode resultCode) {
        ResultData resultData = new ResultData();
        resultData.setCode(resultCode.getCode());
        resultData.setMsg(resultCode.getMessage());
        return resultData;
    }
    
    public static ResultData customizeResult(int code, String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(msg);
        return resultData;
    }
    
    public static ResultData successResult() {
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.SUCCESS.getCode());
        resultData.setMsg(ResultCode.SUCCESS.getMessage());
        return resultData;
    }
    
    public static ResultData failResult() {
        ResultData resultData = new ResultData();
        resultData.setCode(ResultCode.FAIL.getCode());
        resultData.setMsg(ResultCode.FAIL.getMessage());
        return resultData;
    }
    
    public static ResultData customizeResult(int code, String msg, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(code);
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    
    public static ResultData customizeResult(IResultCode resultCode, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(resultCode.getCode());
        resultData.setMsg(resultCode.getMessage());
        resultData.setData(data);
        return resultData;
    }
    
    public static void printJson(HttpServletResponse response, String jsonData) throws IOException {
        printJson(null, response, jsonData);
    }
    
    public static void printJson(HttpServletRequest request, HttpServletResponse response,
                                 String jsonData) throws IOException {
        try {
            Preconditions.checkNotNull(jsonData);
        } catch (Exception e) {
            return;
        }
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonData);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put(ResultBase.CODE_FIELD, this.getCode());
        jsonMap.put(ResultBase.MSG_FIELD, this.getMsg());
        jsonMap.put(ResultData.DATA_FIELD, this.getData());
        try {
            return objectMapper.writeValueAsString(jsonMap);
        } catch (JsonProcessingException e) {
            log.debug("ResultData toString error:\t" + e.getMessage());
        }
        return null;
    }
}
