package com.lkyl.oceanframework.web.annotation;


import com.lkyl.oceanframework.log.annotation.EnableLogRecord;
import com.lkyl.oceanframework.web.selector.OceanWebConfigureSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OceanWebConfigureSelector.class})
public @interface EnableOceanWeb {
    String tenantId() default "";
}
