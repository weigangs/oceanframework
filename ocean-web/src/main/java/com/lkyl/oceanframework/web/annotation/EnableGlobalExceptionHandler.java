package com.lkyl.oceanframework.web.annotation;

import com.lkyl.oceanframework.common.utils.exception.GlobalExceptionController;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = {GlobalExceptionController.class})
public @interface EnableGlobalExceptionHandler {
}
