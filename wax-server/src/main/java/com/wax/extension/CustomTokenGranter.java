//package com.wax.extension;
//
//import com.alibaba.cloud.commons.lang.StringUtils;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import com.wax.utils.ResultData;
//import com.wax.utils.enums.ResultCode;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2RefreshToken;
//import org.springframework.security.oauth2.provider.TokenGranter;
//import org.springframework.security.oauth2.provider.TokenRequest;
//import org.springframework.util.CollectionUtils;
//
//import java.io.IOException;
//import java.util.*;
//
///**
// * @author wax
// * @description: 为了自定义令牌的返回结构
// * @date 2021-10-22
// */
//@Slf4j
//public class CustomTokenGranter implements TokenGranter {
//
//    private final List<TokenGranter> tokenGranters;
//
//    public CustomTokenGranter(List<TokenGranter> tokenGranters) {
//        this.tokenGranters = tokenGranters;
//    }
//
//    @Override
//    public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
//        log.debug("Custom TokenGranter :: grant token with type {}", grantType);
//        for (TokenGranter granter : tokenGranters) {
//            OAuth2AccessToken grant = granter.grant(grantType, tokenRequest);
//            if (grant != null) {
//                return new CustomOAuth2AccessToken(grant);
//            }
//        }
//        return null;
////        // 如果发生异常, 会触发 WebResponseExceptionTranslator
////        final OAuth2AccessToken oAuth2AccessToken =
////                Optional.ofNullable(delegate.grant(grantType, tokenRequest)).orElseThrow(() ->
////                new UnsupportedGrantTypeException("不支持的授权类型!"));
////        return new CustomOAuth2AccessToken(oAuth2AccessToken);
//    }
//
//
//    @com.fasterxml.jackson.databind.annotation.JsonSerialize(using =
//            CustomOAuth2AccessTokenJackson2Serializer.class)
//    public static final class CustomOAuth2AccessToken extends DefaultOAuth2AccessToken {
//
////        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//        public CustomOAuth2AccessToken(OAuth2AccessToken accessToken) {
//            super(accessToken);
//        }
//
//        @SneakyThrows
//        public Map<Object, Object> tokenSerialize() {
//            final LinkedHashMap<Object, Object> map = new LinkedHashMap<>(5);
//            map.put(OAuth2AccessToken.ACCESS_TOKEN, this.getValue());
//            map.put(OAuth2AccessToken.TOKEN_TYPE, this.getTokenType());
//
//            final OAuth2RefreshToken refreshToken = this.getRefreshToken();
//            if (Objects.nonNull(refreshToken)) {
//                map.put(OAuth2AccessToken.REFRESH_TOKEN, refreshToken.getValue());
//            }
//
//            final Date expiration = this.getExpiration();
//            if (Objects.nonNull(expiration)) {
//                map.put(OAuth2AccessToken.EXPIRES_IN,
//                        (expiration.getTime() - System.currentTimeMillis()) / 1000);
//            }
//
//            final Set<String> scopes = this.getScope();
//            if (!CollectionUtils.isEmpty(scopes)) {
//                final StringBuffer buffer = new StringBuffer();
//                scopes.stream().filter(StringUtils::isNotBlank).forEach(scope -> buffer.append(scope).append(" "));
//                map.put(OAuth2AccessToken.SCOPE, buffer.substring(0, buffer.length() - 1));
//            }
//
//            final Map<String, Object> additionalInformation = this.getAdditionalInformation();
//            if (!CollectionUtils.isEmpty(additionalInformation)) {
//                additionalInformation.forEach((key, value) -> map.put(key,
//                        additionalInformation.get(key)));
//            }
//            return map;
//        }
//    }
//
//
//    /**
//     * 自定义 {@link CustomOAuth2AccessToken} 的序列化器
//     */
//    private static final class CustomOAuth2AccessTokenJackson2Serializer extends StdSerializer<CustomOAuth2AccessToken> {
//
//        protected CustomOAuth2AccessTokenJackson2Serializer() {
//            super(CustomOAuth2AccessToken.class);
//        }
//
//        @Override
//        public void serialize(CustomOAuth2AccessToken oAuth2AccessToken,
//                              JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//            jsonGenerator.writeStartObject();
//            jsonGenerator.writeObjectField(ResultData.CODE_FIELD, ResultCode.SUCCESS.getCode());
//            jsonGenerator.writeObjectField(ResultData.MSG_FIELD, ResultCode.SUCCESS.getMessage());
//            jsonGenerator.writeObjectField(ResultData.DATA_FIELD, oAuth2AccessToken.tokenSerialize());
//            jsonGenerator.writeEndObject();
//        }
//    }
//
//}
