package com.lkyl.oceanframework.common.utils.page;

import com.github.pagehelper.PageHelper;
import com.lkyl.oceanframework.common.utils.context.PageContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author nicholas
 * @date 2023/05/28 12:54
 */
@Component
@Aspect
public class PageSelectorAspect {
    /**
     * 对TestService类下面的所有方法拦截.
     */
    @Pointcut("@annotation(com.lkyl.oceanframework.common.utils.annotation.PageSelector)")
    public void pointcut() {
    }


    public void beforeMethod(JoinPoint joinPoint) {

        if (Objects.isNull(PageContext.getPageArgs())) {
            PageHelper.startPage(1, 0);
            return;
        }
        PageArgs pageArgs = PageContext.getPageArgs();
        PageArgs copiedPageArgs = new PageArgs(pageArgs.getPageNum(), pageArgs.getPageSize());
        if (Objects.isNull(copiedPageArgs.getPageNum()) || (copiedPageArgs.getPageNum() < 0)) {
            copiedPageArgs.setPageNum(1);
        }
        if (Objects.isNull(copiedPageArgs.getPageSize())) {
            copiedPageArgs.setPageSize(10);
        }
        PageHelper.startPage(copiedPageArgs.getPageNum(), copiedPageArgs.getPageSize());

    }
    @Around("pointcut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        this.beforeMethod(pjp);

        return pjp.proceed();
    }
}
