package com.lkyl.oceanframework.security.service;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import java.util.Date;

public class OceanTokenServices extends DefaultTokenServices {

    @Resource
    private TokenStore tokenStore;

    @Override
    public OAuth2Authentication loadAuthentication(String accessTokenValue) throws AuthenticationException, InvalidTokenException {
        OAuth2Authentication auth2Authentication =  super.loadAuthentication(accessTokenValue);
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken)tokenStore.getAccessToken(auth2Authentication);
        token.setExpiration(new Date(System.currentTimeMillis() + (long)super.getAccessTokenValiditySeconds(auth2Authentication.getOAuth2Request()) * 1000L));
        return auth2Authentication;
    }
}
