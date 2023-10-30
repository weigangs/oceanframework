package com.lkyl.oceanframework.common.auth.properties;

import com.lkyl.oceanframework.common.utils.constant.OauthConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nicholas
 * @date 2023/05/14 22:10
 */
@ConfigurationProperties(prefix = "ocean.security.oauth2")
@Component
@Data
public class OceanOauth2Properties {

    private List<String> permittedUrls;

    private List<String> corsAllowedOrigins;

    private List<String> corsAllowedMethods;

    private List<RequestMatcher> matchers;

    private String tokenKey = OauthConstant.TOKEN_PREFIX;

    @PostConstruct
    public void afterPropertiesSet() {
        this.matchers = allMatchers();
    }

    public List<RequestMatcher> allMatchers() {
        if (CollectionUtils.isEmpty(permittedUrls)) {
            return Collections.emptyList();
        }
        return permittedUrls.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
    }

    public boolean match(HttpServletRequest request) {
        return matchers.stream().anyMatch(requestMatcher -> requestMatcher.matches(request));
    }

}
