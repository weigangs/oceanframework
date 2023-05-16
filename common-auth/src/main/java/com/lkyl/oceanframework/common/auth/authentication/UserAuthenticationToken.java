package com.lkyl.oceanframework.common.auth.authentication;

import com.lkyl.oceanframework.common.utils.principal.UserPrincipal;
import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

/**
 * @author nicholas
 * @date 2023/05/15 20:46
 */
public class UserAuthenticationToken extends AbstractAuthenticationToken {

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    private String authToken;

    private UserPrincipal principal;

    public UserAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(UserPrincipal userPrincipal) {
        this.principal = userPrincipal;
    }

    @Override
    public boolean implies(Subject subject) {
        return super.implies(subject);
    }
}
