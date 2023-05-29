package com.wax.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wax.utils.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-22
 */
@Slf4j
@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    
    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        final Class<? extends Exception> exceptionClass = e.getClass();
        final String exceptionMessage = e.getMessage();
        
        // AuthenticationException -> 401
        if (AuthenticationException.class.isAssignableFrom(exceptionClass)) {
            return handleOAuth2Exception(exceptionMessage, HttpStatus.UNAUTHORIZED);
        }
        
        // OAuth2Exception -> 403
        if (OAuth2Exception.class.isAssignableFrom(exceptionClass)) {
            return handleOAuth2Exception(exceptionMessage, HttpStatus.FORBIDDEN);
        }
        
        return handleOAuth2Exception(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private ResponseEntity<OAuth2Exception> handleOAuth2Exception(String exceptionMessage,
                                                                  HttpStatus httpStatus) {
        return new ResponseEntity<>(new CustomOAuth2Exception(exceptionMessage, httpStatus),
                httpStatus);
    }
    
    /**
     * Description: 自定义 {@link OAuth2Exception}
     *
     * @author LiKe
     * @date 2020-06-22 16:42:05
     */
    @com.fasterxml.jackson.databind.annotation.JsonSerialize(using =
            CustomOAuth2ExceptionJackson2Serializer.class)
    public static final class CustomOAuth2Exception extends OAuth2Exception {
        
        private final HttpStatus httpStatus;
        
        public CustomOAuth2Exception(String msg, HttpStatus httpStatus) {
            super(msg);
            this.httpStatus = httpStatus;
        }
        
        public HttpStatus getHttpStatus() {
            return httpStatus;
        }
    }
    
    // ~ Serializer
    
    private static final class CustomOAuth2ExceptionJackson2Serializer extends StdSerializer<CustomOAuth2Exception> {
        
        private static final String SERIALIZED_PLAIN_OBJECT = "{}";
        
        protected CustomOAuth2ExceptionJackson2Serializer() {
            super(CustomOAuth2Exception.class);
        }
        
        @Override
        public void serialize(CustomOAuth2Exception e, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeObjectField(ResultData.CODE_FIELD, e.getHttpStatus().value());
            jsonGenerator.writeObjectField(ResultData.MSG_FIELD, e.getMessage());
//            jsonGenerator.writeObjectField(ResultData.DATA_FIELD, SERIALIZED_PLAIN_OBJECT);
            jsonGenerator.writeEndObject();
        }
    }
}
