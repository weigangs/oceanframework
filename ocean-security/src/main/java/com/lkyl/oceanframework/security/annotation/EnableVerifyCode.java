package com.lkyl.oceanframework.security.annotation;

import com.lkyl.oceanframework.security.filter.VerifyCodeFilter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {VerifyCodeFilter.class})
public @interface EnableVerifyCode {
}
