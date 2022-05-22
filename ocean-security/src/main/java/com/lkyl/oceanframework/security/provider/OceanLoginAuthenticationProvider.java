package com.lkyl.oceanframework.security.provider;

import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.security.encoder.ThreePhaseEncoder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月19日 21:19
 */
public class OceanLoginAuthenticationProvider implements AuthenticationProvider, FactoryBean<OceanLoginAuthenticationProvider> {

    @Value("${ocean.security.oauth2.privateKey}")
    private String privateKey;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken loginToken = (UsernamePasswordAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginToken.getName());

        if(ObjectUtils.isEmpty(userDetails)) {
            throw new CommonException(CommonCode.NO_AUTH).setMsg(CommonCode.USER_NOT_FOUND_MSG);
        }

        if(ThreePhaseEncoder.getInstance().matches(loginToken.getCredentials().toString(), userDetails.getPassword(), privateKey)) {
            return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        }

        throw new BadCredentialsException("login failed");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public OceanLoginAuthenticationProvider getObject() throws Exception {
        return this;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }
}
