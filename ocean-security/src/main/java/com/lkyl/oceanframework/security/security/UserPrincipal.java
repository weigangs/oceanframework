package com.lkyl.oceanframework.security.security;

import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.*;

public class UserPrincipal extends OceanUserPrincipal implements UserDetails {

    public UserPrincipal(OceanUserPrincipal userPrincipal, Collection<? extends GrantedAuthority> authorities, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialNotExpired, boolean isEnabled) {
        if(!ObjectUtils.isEmpty(userPrincipal) && !StringUtils.isBlank(userPrincipal.getUserName()) && !StringUtils.isBlank(userPrincipal.getPassword())) {
            this.password = userPrincipal.getPassword();
            this.username = userPrincipal.getUserName();
            this.authorities = Collections.unmodifiableSet(new HashSet<>(authorities));;

            this.isAccountNonExpired = isAccountNonExpired;
            this.isAccountNonLocked = isAccountNonLocked;
            this.isCredentialNotExpired = isCredentialNotExpired;
            this.isEnabled = isEnabled;

            super.setUserId(userPrincipal.getUserId());
            super.setNickName(userPrincipal.getNickName());
        } else {
            throw new CommonException(CommonCode.EXCEPTION, "can not pass null or empty value to constructor");
        }
    }

    private final Set<GrantedAuthority> authorities;

    private final String password;

    private final String username;

    private final boolean isAccountNonExpired;

    private final boolean isAccountNonLocked;
    private final boolean isCredentialNotExpired;
    private final boolean isEnabled;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialNotExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
