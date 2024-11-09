package com.lkyl.oceanframework.codegen.generator;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.constants.CodeGenConstants;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteGeneralJavaClassModel;
import com.lkyl.oceanframework.codegen.utils.FileGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class ControllerFileGenerator implements FileGenerator {

    private final static List<String> DEFAULT_IMPORT_LIST = List.of(
            "java.util.List",
            "javax.annotation.Resource",
            "javax.validation.Valid",
            "org.springframework.web.bind.annotation.PathVariable",
            "org.springframework.web.bind.annotation.PostMapping",
            "org.springframework.web.bind.annotation.GetMapping",
            "org.springframework.web.bind.annotation.PostMapping",
            "org.springframework.web.bind.annotation.RequestBody",
            "org.springframework.web.bind.annotation.RequestMapping",
            "org.springframework.web.bind.annotation.RestController",
            "com.lkyl.oceanframework.common.utils.result.CommonResult",
            "com.lkyl.oceanframework.common.utils.result.PageResult"
    );

    private final static String CLASS_NAME_SUFFIX = "Controller";

    @Override
    public TempldateFileTypeEnum getFileType() {
        return TempldateFileTypeEnum.CONTROLLER;
    }

    @Override
    public void generateFile(CodeGenContext context) {
        ConcreteGeneralJavaClassModel model = buildClassMode(context);
        try {
            FileGeneratorUtils.writeFileWithFreemarker(context, model);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private ConcreteGeneralJavaClassModel buildClassMode(CodeGenContext context) {
        ConcreteGeneralJavaClassModel result = new ConcreteGeneralJavaClassModel();
        result.setPackageName(YamlConfigProperties.getStringProperty("file.generation.location." + getFileType().getType() + ".package"));
        result.setEntityModel(context.getConcreteJavaEntityModel());
        result.setImportList(
                new ArrayList<>(
                        List.of(
                                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.SERVICE.getType()  +  ".package") +
                                "." +
                                context.getConcreteJavaEntityModel().getEntityName() + "Service",
                                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.CREATE_DTO.getType()  +  ".package") +
                                        "." +
                                        context.getConcreteJavaEntityModel().getEntityName() + "CreateDTO",
                                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.UPDATE_DTO.getType()  +  ".package") +
                                        "." +
                                        context.getConcreteJavaEntityModel().getEntityName() + "UpdateDTO",
                                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.DETAIL_VO.getType()  +  ".package") +
                                        "." +
                                        context.getConcreteJavaEntityModel().getEntityName() + "DetailVO",
                                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.PAGE_QUERY_DTO.getType()  +  ".package") +
                                        "." +
                                        context.getConcreteJavaEntityModel().getEntityName() + "PageQueryDTO"
                        )
                )
        );
        result.getImportList().addAll(DEFAULT_IMPORT_LIST);
        result.setTypeEnum(getFileType());
        result.setClassName(context.getConcreteJavaEntityModel().getEntityName() + CLASS_NAME_SUFFIX);
        result.setFileName(result.getClassName() + CodeGenConstants.JAVA_FILE_NAME_EXT);
        return result;
    }
}