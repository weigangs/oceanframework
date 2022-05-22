package com.lkyl.oceanframework.web.util;

import com.lkyl.oceanframework.web.base.BusinessContext;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月21日 11:47
 */
public class ContextUtil {
    private static final InheritableThreadLocal<BusinessContext> businessThreadLocal = new InheritableThreadLocal<>();

    public static void setBusinessContext(BusinessContext businessContext) {
        businessThreadLocal.set(businessContext);
    }

    public static BusinessContext getBusinessContext() {
        return businessThreadLocal.get();
    }

    public static String getTenantId() {
        return getBusinessContext().getHeader().getTanentId();
    }
}
