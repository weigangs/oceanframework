package com.lkyl.oceanframework.codegen.generator;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.constants.CodeGenConstants;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteGeneralJavaClassModel;
import com.lkyl.oceanframework.codegen.utils.FileGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryComponentFileGenerator implements FileGenerator {

    private final static List<String> DEFAULT_IMPORT_LIST = List.of(
            "javax.annotation.Resource",
            "org.springframework.stereotype.Component",
            "java.util.List",
            "com.lkyl.oceanframework.common.utils.annotation.PageSelector"

    );

    private final static String CLASS_NAME_SUFFIX = "QueryComponent";

    @Override
    public TempldateFileTypeEnum getFileType() {
        return TempldateFileTypeEnum.QUERY_COMPONENT;
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
        result.setImportList(new ArrayList<>());
        result.getImportList().addAll(DEFAULT_IMPORT_LIST);
        result.getImportList().addAll(getCommonImportList(context));
        result.setTypeEnum(getFileType());
        result.setClassName(context.getConcreteJavaEntityModel().getEntityName() + CLASS_NAME_SUFFIX);
        result.setFileName(result.getClassName() + CodeGenConstants.JAVA_FILE_NAME_EXT);
        return result;
    }

    private List<String> getCommonImportList(CodeGenContext context) {
        return List.of(
                YamlConfigProperties.getStringProperty("file.generation.location.entity.package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName(),
                YamlConfigProperties.getStringProperty("file.generation.location.mapper.package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "Mapper",
                YamlConfigProperties.getStringProperty("file.generation.location.entity.package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "Example"
        );
    }


}
