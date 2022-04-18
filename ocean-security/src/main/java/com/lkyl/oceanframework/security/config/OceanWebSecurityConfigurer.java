package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.handler.OceanLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class OceanWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

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
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll().
                and().authorizeRequests().anyRequest().authenticated().
                and().logout().logoutSuccessHandler(oceanLogoutSuccessHandler());
    }


}
