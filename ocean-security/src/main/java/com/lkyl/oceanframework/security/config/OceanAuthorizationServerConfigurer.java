package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.converter.OceanAccessTokenConverter;
import com.lkyl.oceanframework.security.handler.OceanAccessDeniedHandler;
import com.lkyl.oceanframework.security.translator.OceanOauthExceptionTranslator;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 配置资源服务器，认证机制
 */
@EnableAuthorizationServer
public class OceanAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private DataSource dataSource;

    @Resource
    private DefaultTokenServices defaultTokenServices;



    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
        authorizationServerSecurityConfigurer.allowFormAuthenticationForClients().
                checkTokenAccess("permitAll()").tokenKeyAccess("isAuthenticated()").accessDeniedHandler(new OceanAccessDeniedHandler());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        clientDetailsServiceConfigurer.jdbc(dataSource);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
        //设置自定义用户信息转换器，如果不设置，SecurityContextHolder获取到用户信息只有username
        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
        defaultAccessTokenConverter.setUserTokenConverter(new OceanAccessTokenConverter());
        authorizationServerEndpointsConfigurer
                .tokenServices(defaultTokenServices)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
                .exceptionTranslator(new OceanOauthExceptionTranslator())
                .accessTokenConverter(defaultAccessTokenConverter);
    }

}
