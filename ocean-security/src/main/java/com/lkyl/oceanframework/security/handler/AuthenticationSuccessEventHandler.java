package com.lkyl.oceanframework.security.handler;

import com.lkyl.oceanframework.security.event.LoginSuccessEvent;
import com.lkyl.oceanframework.web.util.BusinessContextUtil;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;

import java.security.Principal;

/**
 * 认证成功后，做处理
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 10:36
 */

public class AuthenticationSuccessEventHandler {
    @Async
    @EventListener( { LoginSuccessEvent.class } )
    public void handlerSuccess(AuthenticationSuccessEvent event) {
        Authentication principal = event.getAuthentication();

        BusinessContextUtil.getBusinessContext().setUser((Principal) event.getAuthentication().getPrincipal());

    }
}
