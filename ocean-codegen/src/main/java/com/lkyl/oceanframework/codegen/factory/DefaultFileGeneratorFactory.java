package com.lkyl.oceanframework.codegen.factory;

import com.lkyl.oceanframework.codegen.generator.ControllerFileGenerator;
import com.lkyl.oceanframework.codegen.generator.ConverterFileGenerator;
import com.lkyl.oceanframework.codegen.generator.CreatationDtoFileGenerator;
import com.lkyl.oceanframework.codegen.generator.DetailVoFileGenerator;
import com.lkyl.oceanframework.codegen.generator.FileGenerator;
import com.lkyl.oceanframework.codegen.generator.MybatisBaseFilesGenerator;
import com.lkyl.oceanframework.codegen.generator.PageQueryDtoFileGenerator;
import com.lkyl.oceanframework.codegen.generator.QueryComponentFileGenerator;
import com.lkyl.oceanframework.codegen.generator.ServiceFileGenerator;
import com.lkyl.oceanframework.codegen.generator.ServiceImplFileGenerator;
import com.lkyl.oceanframework.codegen.generator.UpdateDtoFileGenerator;

public class DefaultFileGeneratorFactory  implements FileGeneratorFactory{




    @Override
    public FileGenerator generateCreateDto() {
        return new CreatationDtoFileGenerator();
    }

    @Override
    public FileGenerator generatePageQueryDto() {
        return new PageQueryDtoFileGenerator();
    }

    @Override
    public FileGenerator generateUpdateDto() {
        return new UpdateDtoFileGenerator();
    }

    @Override
    public FileGenerator generateDetailVo() {
        return new DetailVoFileGenerator();
    }

    @Override
    public FileGenerator generateService() {
        return new ServiceFileGenerator();
    }

    @Override
    public FileGenerator generateServiceImpl() {
        return new ServiceImplFileGenerator();
    }

    @Override
    public FileGenerator generateConverter() {
        return new ConverterFileGenerator();
    }

    @Override
    public FileGenerator generateController() {
        return new ControllerFileGenerator();
    }

    @Override
    public FileGenerator generateQueryComponent() {
        return new QueryComponentFileGenerator();
    }

    @Override
    public FileGenerator generateMybatisBaseFiles() {
        return new MybatisBaseFilesGenerator();
    }
}
