package com.wax.extension.captcha;

import cn.hutool.core.util.StrUtil;
import com.wax.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wax
 * @version 1.0.0
 * @date 2023/4/25
 * @description 拓展图形验证码登录
 */
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter {


    /**
     * 声明授权者 CaptchaTokenGranter 支持授权模式 captcha
     * 根据接口传值 grant_type = captcha 的值匹配到此授权者
     * 匹配逻辑详见下面的两个方法
     *
     * @see CompositeTokenGranter#grant(String, TokenRequest)
     * @see AbstractTokenGranter#grant(String, TokenRequest)
     */
    private static final String GRANT_TYPE = "captcha";
    private final AuthenticationManager authenticationManager;
    private StringRedisTemplate redisTemplate;
    private PasswordEncoder passwordEncoder;

    public CaptchaTokenGranter(AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory,
                               AuthenticationManager authenticationManager,
                               StringRedisTemplate redisTemplate, PasswordEncoder passwordEncoder
    ) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,
                                                           TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());

        // 验证码校验逻辑
        String validateCode = parameters.get("verifyCode");
        String uuid = parameters.get("verifyCodeKey");

//        if (StrUtil.isBlank(validateCode)){
//            throw new BadCredentialsException("验证码不能为空");
//        }
        Assert.isTrue(StrUtil.isNotBlank(validateCode), "验证码不能为空");
        String validateCodeKey = Constants.VERIFY_CODE_KEY_PREFIX + uuid;

        // 从缓存取出正确的验证码和用户输入的验证码比对
        // String correctValidateCode = redisTemplate.opsForValue().get(validateCodeKey);
        String correctValidateCode = "666666";
        Assert.isTrue(StrUtil.isNotBlank(correctValidateCode), "验证码已过期");
        Assert.isTrue(validateCode.equals(correctValidateCode), "您输入的验证码不正确");

        // 验证码验证通过，删除 Redis 的验证码
        redisTemplate.delete(validateCodeKey);

        String username = parameters.get("username");
        String password = parameters.get("password");

        // 移除后续无用参数
        parameters.remove("password");
        parameters.remove("verifyCode");
        parameters.remove("verifyCodeKey");

        // 和密码模式一样的逻辑  可以自定义UsernamePasswordAuthenticationToken
        // （AbstractAuthenticationToken）来判断 和 AuthenticationProvider
        // 来实现 自定义异常返回密码错误
        Authentication userAuth = new CaptchaAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        try {
            userAuth = this.authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException var8) {
            throw new InvalidGrantException(var8.getMessage());
        } catch (BadCredentialsException var9) {
            throw new InvalidGrantException(var9.getMessage());
        }


        if (userAuth != null && userAuth.isAuthenticated()) {
            OAuth2Request storedOAuth2Request =
                    this.getRequestFactory().createOAuth2Request(client, tokenRequest);
            return new OAuth2Authentication(storedOAuth2Request, userAuth);
        } else {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }
    }
}
