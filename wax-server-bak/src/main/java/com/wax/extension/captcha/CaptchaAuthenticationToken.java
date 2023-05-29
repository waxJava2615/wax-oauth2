package com.wax.extension.captcha;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author wax
 * @description: TODO
 * @date 2021-10-29
 */
public class CaptchaAuthenticationToken extends AbstractAuthenticationToken {
    
    private final Object principal;
    private Object credentials;
    
    public CaptchaAuthenticationToken(Object principal, Object credentials) {
        super((Collection) null);
        this.principal = principal;
        this.credentials = credentials;
        this.setAuthenticated(false);
    }
    
    public CaptchaAuthenticationToken(Object principal, Object credentials,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }
    
    
    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a " +
                            "GrantedAuthority list instead");
        }
        
        super.setAuthenticated(false);
    }
    
    
    @Override
    public Object getCredentials() {
        return this.credentials;
    }
    
    
    @Override
    public Object getPrincipal() {
        return this.principal;
    }
    
    /**
     * Checks the {@code credentials}, {@code principal} and {@code details} objects,
     * invoking the {@code eraseCredentials} method on any which implement
     * {@link CredentialsContainer}.
     */
    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
