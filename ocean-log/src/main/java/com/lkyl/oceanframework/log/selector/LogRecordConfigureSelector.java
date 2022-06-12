package com.lkyl.oceanframework.log.selector;

import com.lkyl.oceanframework.log.config.LogRecordProxyAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月29日 23:09
 */
public class LogRecordConfigureSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{LogRecordProxyAutoConfiguration.class.getName()};
    }
}
