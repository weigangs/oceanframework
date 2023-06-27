package com.lkyl.oceanframework.common.cache.limit.annotation;

import com.lkyl.oceanframework.common.cache.limit.enums.LimitType;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RedisLimitAnnotation {
 
    /**
     * key
     */
    String key() default "";
    /**
     * Key的前缀
     */
    String prefix() default "";
    /**
     * 一定时间内最多访问次数
     */
    int count();
    /**
     * 给定的时间范围 单位(秒)
     */
    int period();
    /**
     * 限流的类型(用户自定义key或者请求ip)
     */
    LimitType limitType() default LimitType.CUSTOMER;
 
}