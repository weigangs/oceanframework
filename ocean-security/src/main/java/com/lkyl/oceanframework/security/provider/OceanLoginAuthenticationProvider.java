package com.lkyl.oceanframework.security.provider;

import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.exception.SystemExceptionEnum;
import com.lkyl.oceanframework.security.encoder.ThreePhaseEncoder;
import com.lkyl.oceanframework.security.event.LoginSuccessEvent;
import com.lkyl.oceanframework.security.event.PasswordIncorrectEvent;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * 对于jar中的类，以@Import方式导入到bean容器中
 * 需实现FactoryBean接口，spring容器会对该类做bean初始化操作
 * 如果不做bean初始化操作，@Value获取不到配置文件的值
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月19日 21:19
 */
public class OceanLoginAuthenticationProvider implements AuthenticationProvider, FactoryBean<OceanLoginAuthenticationProvider> {

    @Value("${ocean.security.oauth2.privateKey}")
    private String privateKey;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken loginToken = (UsernamePasswordAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginToken.getName());

        if(ObjectUtils.isEmpty(userDetails)) {
            throw new CommonException(SystemExceptionEnum.PERMISSION_DENY);
        }

        if(ThreePhaseEncoder.getInstance().matches(loginToken.getCredentials().toString(), userDetails.getPassword(), privateKey)) {
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            //发布自定义登录成功事件
            publisher.publishEvent(new LoginSuccessEvent(auth));
            return auth;
        }

        BadCredentialsException exception = new BadCredentialsException("login failed");
        //发布自定义密码错误事件
        publisher.publishEvent(new PasswordIncorrectEvent(authentication, exception));
        throw exception;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public OceanLoginAuthenticationProvider getObject() throws Exception {
        return this;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }
}
