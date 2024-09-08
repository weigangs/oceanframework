package com.lkyl.oceanframework.codegen.generator;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.constants.CodeGenConstants;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteGeneralJavaClassModel;
import com.lkyl.oceanframework.codegen.model.freemarker.JavaFieldModel;
import com.lkyl.oceanframework.codegen.utils.FileGeneratorUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DetailVoFileGenerator implements FileGenerator{

    private final static List<String> DEFAULT_IMPORT_LIST = List.of(
            "lombok.Data",
            "lombok.ToString",
            "io.swagger.annotations.ApiModelProperty",
            "com.fasterxml.jackson.databind.annotation.JsonSerialize",
            "com.fasterxml.jackson.databind.ser.std.ToStringSerializer"
    );

    private final static String CLASS_NAME_SUFFIX = "DetailVO";
    @Override
    public TempldateFileTypeEnum getFileType() {
        return TempldateFileTypeEnum.DETAIL_VO;
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
                context.getConcreteJavaEntityModel().getFieldList()
                        .stream().map(JavaFieldModel::getJavaFullyFieldType)
                        .filter(javaFullyFieldType -> !javaFullyFieldType.startsWith(JAVA_LANG_PACKAGE_PREFIX))
                        .distinct().collect(Collectors.toList())
        );
        result.getImportList().addAll(DEFAULT_IMPORT_LIST);
        result.setTypeEnum(getFileType());
        result.setClassName(context.getConcreteJavaEntityModel().getEntityName() + CLASS_NAME_SUFFIX);
        result.setFileName(result.getClassName() + CodeGenConstants.JAVA_FILE_NAME_EXT);
        return result;
    }


}
