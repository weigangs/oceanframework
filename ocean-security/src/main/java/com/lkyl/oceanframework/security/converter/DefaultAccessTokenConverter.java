package com.lkyl.oceanframework.security.converter;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * OAUTH2 认证信息处理，使用securityUtil 获取到用户完整信息
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月19日 22:47
 */
public class DefaultAccessTokenConverter extends DefaultUserAuthenticationConverter {
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("user_name", authentication);
        return response;
    }
}
