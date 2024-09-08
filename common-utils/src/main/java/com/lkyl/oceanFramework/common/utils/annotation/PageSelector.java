package com.lkyl.oceanframework.common.utils.annotation;

<<<<<<< HEAD
import java.lang.annotation.*;
=======
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
>>>>>>> develop_2023_1.1.1

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageSelector {
}
