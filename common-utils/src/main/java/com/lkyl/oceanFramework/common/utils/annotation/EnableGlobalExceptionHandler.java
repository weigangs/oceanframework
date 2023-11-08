package com.lkyl.oceanframework.common.utils.annotation;

import com.lkyl.oceanframework.common.utils.exception.GlobalExceptionController;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {GlobalExceptionController.class})
public @interface EnableGlobalExceptionHandler {
}
