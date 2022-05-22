package com.lkyl.oceanframework.web.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月21日 11:49
 */
public class WebBusinessContext  implements BusinessContext{
    private final Header header;
    private final ConcurrentHashMap<String, Object> parameterMap = new ConcurrentHashMap<>();

    public WebBusinessContext(Header header) {
        this.header = header;
    }
    @Override
    public Header getHeader() {
        return this.header;
    }

    @Override
    public Map<String, Object> getParameterMap() {
        return this.parameterMap;
    }

}
