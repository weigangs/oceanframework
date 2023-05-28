package com.lkyl.oceanframework.common.utils.page;

import com.github.pagehelper.PageHelper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof PageArgs) {
                PageHelper.startPage(((PageArgs) arg).getPageNum(), ((PageArgs) arg).getPageSize());
                return;
            }
        }

        throw new IllegalArgumentException("can not find Page params");
    }
//
//    public void afterMethod(JoinPoint joinPoint) {
//    }

    //@Around：环绕通知
    @Around("pointcut()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
        this.beforeMethod(pjp);

        return pjp.proceed();
    }
}
