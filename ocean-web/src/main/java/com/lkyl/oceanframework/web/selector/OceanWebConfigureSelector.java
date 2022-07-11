package com.lkyl.oceanframework.web.selector;

import com.lkyl.oceanframework.web.config.OceanWebAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年07月10日 23:28
 */
public class OceanWebConfigureSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{OceanWebAutoConfiguration.class.getName()};
    }
}
