package com.lkyl.oceanframework.web.base.impl;


import com.lkyl.oceanframework.web.base.BusinessContext;
import com.lkyl.oceanframework.web.base.Header;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月21日 11:49
 */
public class WebBusinessContext  implements BusinessContext {
    private final Header header;
    private final ConcurrentHashMap<String, Object> parameterMap = new ConcurrentHashMap<>();
    private Principal principal;
    public WebBusinessContext(Header header, Principal principal) {
        this.header = header;
        this.principal = principal;
    }
    @Override
    public Header getHeader() {
        return this.header;
    }

    @Override
    public Map<String, Object> getParameterMap() {
        return this.parameterMap;
    }

    @Override
    public String getUserName() {
        return principal.getName();
    }

    @Override
    public Principal getUser() {
        return principal;
    }

    @Override
    public void setUser(Principal principal) {
        this.principal = principal;
    }

}
