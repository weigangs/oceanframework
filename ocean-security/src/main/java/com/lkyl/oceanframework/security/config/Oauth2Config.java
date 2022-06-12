package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.handler.AuthenticationSuccessEventHandler;
import com.lkyl.oceanframework.security.service.OceanTokenServices;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * 配置oauth2 基础组件
 */
public class Oauth2Config {

    @Resource
    private ClientDetailsService clientDetailsService;

    @Resource
    private TokenStore tokenStore;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new OceanTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService);
        return tokenServices;

    }

//    @Bean(name = "daoAuthenticationProvider")
//    public AuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//        return daoAuthenticationProvider;
//    }


    /**
     * 认证事件发布
     * @param applicationEventPublisher
     * @return
     */
    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher
            (ApplicationEventPublisher applicationEventPublisher) {

        AuthenticationEventPublisher authenticationEventPublisher =
                new DefaultAuthenticationEventPublisher(applicationEventPublisher);
        return authenticationEventPublisher;
    }

    /**
     * 认证成功处理逻辑
     * @return
     */
    @Bean
    public AuthenticationSuccessEventHandler authenticationSuccessEventHandler() {
        return new AuthenticationSuccessEventHandler();
    }
}
