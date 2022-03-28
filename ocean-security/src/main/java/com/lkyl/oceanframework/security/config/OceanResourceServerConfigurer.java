package com.lkyl.oceanframework.security.config;

import com.lkyl.oceanframework.security.exception.AuthExceptionEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

@Configuration
@EnableResourceServer
public class OceanResourceServerConfigurer implements ResourceServerConfigurer {

    @Resource
    private OceanOauth2Properties oceanOauth2Properties;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(new RedisTokenStore(redisConnectionFactory));
        return tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resourceServerSecurityConfigurer) throws Exception {
        resourceServerSecurityConfigurer.
                tokenServices(defaultTokenServices()).
                resourceId(oceanOauth2Properties.getResourceId()).
                authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.requestMatchers().anyRequest().
                and().authorizeRequests().
                antMatchers("/user/login").
                permitAll();


    }
}
