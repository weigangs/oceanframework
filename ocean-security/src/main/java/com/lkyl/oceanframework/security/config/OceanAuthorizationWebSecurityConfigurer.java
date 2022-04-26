package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.handler.OceanLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class OceanAuthorizationWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

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
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll().
                and().authorizeRequests().anyRequest().authenticated().
                and().logout().logoutSuccessHandler(oceanLogoutSuccessHandler());
    }


}
