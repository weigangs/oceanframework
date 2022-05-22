package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.handler.OceanLogoutSuccessHandler;
import com.lkyl.oceanframework.security.provider.OceanLoginAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class OceanWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Resource
    private OceanLoginAuthenticationProvider oceanLoginAuthenticationProvider;

    @Bean
    public OceanLogoutSuccessHandler oceanLogoutSuccessHandler(){
        return new OceanLogoutSuccessHandler();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(oceanLoginAuthenticationProvider);
    }
}
