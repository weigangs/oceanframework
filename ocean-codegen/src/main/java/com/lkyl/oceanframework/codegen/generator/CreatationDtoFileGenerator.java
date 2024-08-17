package com.lkyl.oceanframework.codegen.generator;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.constants.CodeGenConstants;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteGeneralJavaClassModel;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteJavaEntityModel;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteJavaFieldModel;
import com.lkyl.oceanframework.codegen.model.freemarker.JavaFieldModel;
import com.lkyl.oceanframework.codegen.utils.FileGeneratorUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CreatationDtoFileGenerator implements FileGenerator{

    private final static List<String> DEFAULT_IMPORT_LIST = List.of(
            "lombok.Data",
            "lombok.ToString",
            "io.swagger.annotations.ApiModelProperty"
    );

    private final static String CLASS_NAME_SUFFIX = "CreateDTO";
    @Override
    public TempldateFileTypeEnum getFileType() {
        return TempldateFileTypeEnum.CREATE_DTO;
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
        result.setEntityModel(copyJavaModel(context.getConcreteJavaEntityModel()));
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

    private ConcreteJavaEntityModel copyJavaModel(ConcreteJavaEntityModel concreteJavaEntityModel) {
        List excludeFields = YamlConfigProperties.getYmlProperty("file.generation.createDto.excludes.javaFields", List.class);
        if (Objects.isNull(excludeFields)) {
            excludeFields = Collections.emptyList();
        }
        Set<String> excludesFieldSet = new HashSet<>(excludeFields);
        return new ConcreteJavaEntityModel(concreteJavaEntityModel.getEntityName(),
                concreteJavaEntityModel.getFieldList().stream()
                        .filter(e -> !excludesFieldSet.contains(e.getFieldName())).collect(Collectors.toUnmodifiableList()));
    }


}
