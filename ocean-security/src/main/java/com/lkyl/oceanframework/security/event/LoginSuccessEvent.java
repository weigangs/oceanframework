package com.lkyl.oceanframework.security.event;

import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;

/**
 * 自定义登录成功事件
 * 在登录provider中手动发布
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 11:25
 */
public class LoginSuccessEvent extends AuthenticationSuccessEvent {
    public LoginSuccessEvent(Authentication authentication) {
        super(authentication);
    }
}
