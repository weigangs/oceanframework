package com.lkyl.oceanframework.web.base;

import java.util.Map;

public interface BusinessContext {

    Header getHeader();

    Map<String, Object> getParameterMap();
}
