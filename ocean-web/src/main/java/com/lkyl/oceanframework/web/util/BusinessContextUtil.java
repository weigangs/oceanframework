package com.lkyl.oceanframework.web.util;


import com.lkyl.oceanframework.web.base.BusinessContext;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月21日 11:47
 */
public class BusinessContextUtil {
    private static final InheritableThreadLocal<BusinessContext> BUSINESS_CONTEXT_INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void setBusinessContext(BusinessContext businessContext) {
        BUSINESS_CONTEXT_INHERITABLE_THREAD_LOCAL.set(businessContext);
    }

    public static BusinessContext getBusinessContext() {
        return BUSINESS_CONTEXT_INHERITABLE_THREAD_LOCAL.get();
    }

    public static String getTenantId() {
        return getBusinessContext().getHeader().getTanentId();
    }
}
