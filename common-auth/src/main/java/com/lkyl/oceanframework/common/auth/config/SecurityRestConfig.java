package com.lkyl.oceanframework.common.auth.config;/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import com.lkyl.oceanframework.common.auth.exception.AuthExceptionEntryPoint;
import com.lkyl.oceanframework.common.auth.exception.OceanAccessDeniedHandler;
import com.lkyl.oceanframework.common.auth.filter.TokenCheckFilter;
import com.lkyl.oceanframework.common.auth.properties.OceanOauth2Properties;
import com.lkyl.oceanframework.common.auth.token.TokenService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Collections;

/**
 * Security configuration for the main application.
 *
 * @author Josh Cummings
 */
@Configuration
public class SecurityRestConfig {
    @Value("${jwt.private.key}")
    RSAPrivateKey priv;
    @Value("${jwt.public.key}")
    RSAPublicKey key;

    @Resource
    private OceanOauth2Properties oceanOauth2Properties;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers(oceanOauth2Properties.getPermittedUrls().toArray(new String[]{})).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .logout().disable()
                .addFilterAfter(tokenCheckFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new AuthExceptionEntryPoint())
                        .accessDeniedHandler(new OceanAccessDeniedHandler())
                );
        // @formatter:on
        return http.build();
    }
//
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        if (!CollectionUtils.isEmpty(oceanOauth2Properties.getCorsAllowedOrigins())) {

            configuration.setAllowedOrigins(oceanOauth2Properties.getCorsAllowedOrigins());
        } else{
             configuration.setAllowedOrigins(Collections.singletonList("*"));
        }
        if (!CollectionUtils.isEmpty(oceanOauth2Properties.getCorsAllowedMethods())) {
            configuration.setAllowedMethods(oceanOauth2Properties.getCorsAllowedMethods());
        } else{
            configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(),
                    HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
        }
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //对所有url生效
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public TokenService tokenService() {
        return new TokenService();
    }

    @Bean
    public TokenCheckFilter tokenCheckFilter() {
        return new TokenCheckFilter();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.key).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(this.key).privateKey(this.priv).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

}