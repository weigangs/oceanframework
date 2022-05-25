package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.exception.AuthExceptionEntryPoint;
import com.lkyl.oceanframework.security.filter.VerifyCodeFilter;
import com.lkyl.oceanframework.security.handler.OceanAccessDeniedHandler;
import com.lkyl.oceanframework.security.handler.OceanLoginFailureHandler;
import com.lkyl.oceanframework.security.handler.OceanLoginSuccessHandler;
import com.lkyl.oceanframework.security.handler.OceanLogoutSuccessHandler;
import com.lkyl.oceanframework.web.filter.ExceptionHandlerFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableResourceServer
public class OceanResourceServerConfigurer extends ResourceServerConfigurerAdapter {

    @Resource
    private OceanLogoutSuccessHandler oceanLogoutSuccessHandler;

    @Resource
    private OceanOauth2Properties oceanOauth2Properties;

    @Resource
    private DefaultTokenServices defaultTokenServices;

    @Resource
    private VerifyCodeFilter verifyCodeFilter;

    @Resource
    private ExceptionHandlerFilter exceptionHandlerFilter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
        resourceServerSecurityConfigurer.
                tokenServices(defaultTokenServices).
                resourceId(oceanOauth2Properties.getResourceId())
                .authenticationEntryPoint(new AuthExceptionEntryPoint());
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, VerifyCodeFilter.class);
        http.authorizeRequests()
                .antMatchers("/oauth/**",
                        "/verify/code",
                        "/health",
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs").permitAll().
                and().authorizeRequests().anyRequest().authenticated().
//                and().logout().logoutSuccessHandler(oceanLogoutSuccessHandler).
                and().logout().addLogoutHandler(oceanLogoutSuccessHandler).clearAuthentication(true).
                and().exceptionHandling().accessDeniedHandler(new OceanAccessDeniedHandler()).
                and().formLogin().disable();
    }

}
