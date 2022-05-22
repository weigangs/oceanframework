package com.lkyl.oceanframework.common.utils.mapperstruct.base;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mappings;

import java.util.Collection;
import java.util.List;

public interface BaseMapperConverter<SOURCE, TARGET> {
    /**
     * 如有需要自定义该注解即可
     * 例如：
     *
     * @Mappings({
     * @Mapping(source = "code", target = "categoryCode"),
     * @Mapping(source = "name", target = "categoryName")
     * })
     * <p></p>
     * 重写此注解时一定要注意 返回值（TARGET） 和 参数（SOURCE） 的顺序
     */
    @Mappings({})
    @InheritConfiguration
    TARGET to(SOURCE source);

    @InheritConfiguration
    List<TARGET> to(Collection<SOURCE> source);

    @InheritInverseConfiguration
    SOURCE from(TARGET source);

    @InheritInverseConfiguration
    List<SOURCE> from(Collection<TARGET> source);
}
