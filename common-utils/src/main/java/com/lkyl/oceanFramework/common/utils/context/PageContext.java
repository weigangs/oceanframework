package com.lkyl.oceanframework.common.utils.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.lkyl.oceanframework.common.utils.page.PageArgs;

public class PageContext {

    private static final ThreadLocal<PageArgs> pageArgsLocal = new TransmittableThreadLocal<>();
    public static PageArgs getPageArgs() {
        return pageArgsLocal.get();
    }

    public static void setPageArgs(PageArgs pageArgs) {
        pageArgsLocal.set(pageArgs);
    }

    public static void removePageArgs() {
        pageArgsLocal.remove();
    }
}
