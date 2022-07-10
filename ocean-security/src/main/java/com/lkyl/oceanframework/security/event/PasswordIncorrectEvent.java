package com.lkyl.oceanframework.security.event;

import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 自定义密码错误事件
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月31日 22:53
 */
public class PasswordIncorrectEvent extends AuthenticationFailureBadCredentialsEvent {
    public PasswordIncorrectEvent(Authentication authentication, AuthenticationException exception) {
        super(authentication, exception);
    }
}
