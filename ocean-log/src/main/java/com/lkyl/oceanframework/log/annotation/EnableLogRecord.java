package com.lkyl.oceanframework.log.annotation;

import com.lkyl.oceanframework.log.selector.LogRecordConfigureSelector;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author nicholas
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LogRecordConfigureSelector.class)
public @interface EnableLogRecord {

    String tenantId();

    AdviceMode mode() default AdviceMode.PROXY;
}
