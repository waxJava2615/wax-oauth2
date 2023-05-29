package com.wax.config;

import com.wax.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-22
 */
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        log.debug("Custom AccessDeniedHandler triggered with exception: {}.",
                accessDeniedException.getClass().getCanonicalName());
        ResultData resultData = ResultData.customizeResult(HttpStatus.UNAUTHORIZED.value(),
                accessDeniedException.getMessage());
        ResultData.printJson(response, resultData.toString());
    }
}
