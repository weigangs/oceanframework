package com.lkyl.oceanframework.security.generator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 生成token加工处理
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年04月25日 22:06
 */
public class OceanOauth2TokenGenerator extends DefaultAuthenticationKeyGenerator {

    private static final String CLIENT_ID = "clientId";

    private static final String SCOPE = "scope";

    private static final String USERNAME = "username";

    @Override
    public String extractKey(OAuth2Authentication authentication) {
            Map<String, String> values = new LinkedHashMap<String, String>();
            OAuth2Request authorizationRequest = authentication.getOAuth2Request();
            if (!authentication.isClientOnly()) {
                values.put(USERNAME, authentication.getName());
            }
            if (authorizationRequest.getScope() != null) {
                values.put(SCOPE, OAuth2Utils.formatParameterList(new TreeSet<String>(authorizationRequest.getScope())));
            }
            if (null != authentication.getOAuth2Request().getClientId() && StringUtils.isNotBlank(authentication.getOAuth2Request().getClientId())){
                values.put(CLIENT_ID, authentication.getOAuth2Request().getClientId());
            }

            // 如果是多租户系统，这里要区分租户ID 条件
            return generateKey(values);
    }
}
