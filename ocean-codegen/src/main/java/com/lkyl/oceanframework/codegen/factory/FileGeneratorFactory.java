package com.lkyl.oceanframework.codegen.factory;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.generator.FileGenerator;

public interface FileGeneratorFactory {

    static  FileGeneratorFactory getFactory() {
        String mybatisStyle = YamlConfigProperties.getStringProperty("mybatis.generator.style");
        if ("dynamic".equals(mybatisStyle)) {
            return new DynamicSqlFileGeneratorFactory();
        }
        return new DefaultFileGeneratorFactory();
    }

    FileGenerator generateCreateDto();
    FileGenerator generatePageQueryDto();
    FileGenerator generateUpdateDto();

    FileGenerator generateDetailVo();

    FileGenerator generateService();

    FileGenerator generateServiceImpl();

    FileGenerator generateConverter();

    FileGenerator generateController();

    FileGenerator generateQueryComponent();

    FileGenerator generateMybatisBaseFiles();
}
