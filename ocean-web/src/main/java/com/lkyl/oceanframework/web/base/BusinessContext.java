package com.lkyl.oceanframework.web.base;

import java.security.Principal;
import java.util.Map;

public interface BusinessContext {

    Header getHeader();

    Map<String, Object> getParameterMap();

    String getUserName();

    Principal getUser();

    void setUser(Principal principal);
}
