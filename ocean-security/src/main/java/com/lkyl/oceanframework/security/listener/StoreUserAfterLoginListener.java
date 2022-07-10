package com.lkyl.oceanframework.security.listener;

import com.lkyl.oceanframework.security.event.LoginSuccessEvent;
import com.lkyl.oceanframework.web.util.BusinessContextUtil;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import java.security.Principal;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月10日 16:14
 */
public class StoreUserAfterLoginListener {
    @Async
    @EventListener( { LoginSuccessEvent.class } )
    public void handlerSuccess(AuthenticationSuccessEvent event) {

        BusinessContextUtil.getBusinessContext().setUser((Principal) event.getAuthentication().getPrincipal());

    }
}
