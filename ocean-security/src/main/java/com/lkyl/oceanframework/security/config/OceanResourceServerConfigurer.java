package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.exception.AuthExceptionEntryPoint;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import javax.annotation.Resource;

@EnableResourceServer
public class OceanResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Resource
    private OceanOauth2Properties oceanOauth2Properties;

    @Resource
    private DefaultTokenServices defaultTokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
        resourceServerSecurityConfigurer.
                tokenServices(defaultTokenServices).
                resourceId(oceanOauth2Properties.getResourceId()).
                authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs")
                .permitAll();
    }

}
