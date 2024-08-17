package com.lkyl.oceanframework.codegen.generator.dynamic;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.constants.CodeGenConstants;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import com.lkyl.oceanframework.codegen.generator.FileGenerator;
import com.lkyl.oceanframework.codegen.generator.ServiceFileGenerator;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteGeneralJavaClassModel;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteJavaEntityModel;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteJavaFieldModel;
import com.lkyl.oceanframework.codegen.model.freemarker.JavaFieldModel;
import com.lkyl.oceanframework.codegen.model.freemarker.ServiceImplJavaEntityModel;
import com.lkyl.oceanframework.codegen.utils.FileGeneratorUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DynamicServiceImplFileGenerator extends ServiceFileGenerator implements FileGenerator {

    private final static List<String> DEFAULT_IMPORT_LIST = List.of(
            "javax.annotation.Resource",
            "org.springframework.stereotype.Service",
            "com.lkyl.oceanframework.common.utils.utils.IdGenerator",
            "com.lkyl.oceanframework.common.utils.enums.YesOrNoEnum",
            "com.lkyl.oceanframework.web.context.UserContext",
            "java.util.List",
            "java.time.LocalDateTime",
            "org.mybatis.dynamic.sql.SqlBuilder",
            "org.mybatis.dynamic.sql.render.RenderingStrategies",
            "org.mybatis.dynamic.sql.select.render.SelectStatementProvider"

    );

    private final static String CLASS_NAME_SUFFIX = "ServiceImpl";
    @Override
    public TempldateFileTypeEnum getFileType() {
        return TempldateFileTypeEnum.DYNAMIC_SERVICE_IMPL;
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
        result.setEntityModel(buildJavaModelFromRaw(context.getConcreteJavaEntityModel()));
        result.setImportList(new ArrayList<>());
        result.getImportList().addAll(DEFAULT_IMPORT_LIST);
        result.getImportList().addAll(getCommonImportList(context));
        result.getImportList().addAll(getCreationMethodImportList(context));
        result.getImportList().addAll(getUpdateMethodImportList(context));
        result.getImportList().addAll(getDetailMethodImportList(context));
        result.setTypeEnum(getFileType());
        result.setClassName(context.getConcreteJavaEntityModel().getEntityName() + CLASS_NAME_SUFFIX);
        result.setFileName(result.getClassName() + CodeGenConstants.JAVA_FILE_NAME_EXT);
        return result;
    }

    private ServiceImplJavaEntityModel buildJavaModelFromRaw(ConcreteJavaEntityModel concreteJavaEntityModel) {
        List excludeFields = YamlConfigProperties.getYmlProperty("file.generation.createDto.excludes.javaFields", List.class);
        if (Objects.isNull(excludeFields)) {
            excludeFields = Collections.emptyList();
        }
        Set<String> excludesFieldSet = new HashSet<>(excludeFields);
        return new ServiceImplJavaEntityModel(concreteJavaEntityModel.getEntityName(),
                concreteJavaEntityModel.getFieldList(),
                concreteJavaEntityModel.getFieldList().stream()
                        .map(buildCreateFieldWithDefaultValue()).collect(Collectors.toUnmodifiableList()),
                concreteJavaEntityModel.getFieldList().stream()
                        .filter(e -> !excludesFieldSet.contains(e.getFieldName())).collect(Collectors.toUnmodifiableList())
        );
    }

    private Function<JavaFieldModel, ConcreteJavaFieldModel> buildCreateFieldWithDefaultValue() {
        Map field2DefaultValueMap = Optional.ofNullable(
                YamlConfigProperties.getYmlProperty("defaultValue.javaFields", Map.class)
                ).orElseGet(Collections::emptyMap);
        return fieldMode -> {
            Object defaultValue = field2DefaultValueMap.get(fieldMode.getFieldName());
            return new ConcreteJavaFieldModel(
                    fieldMode.getJavaFieldType(),
                    fieldMode.getJavaFullyFieldType(),
                    fieldMode.getFieldName(),
                    fieldMode.isNullable(),
                    Objects.isNull(defaultValue) ? null : defaultValue.toString(),
                    fieldMode.getRemark()
            );
        };

    }

    private List<String> getCommonImportList(CodeGenContext context) {
        return List.of(
                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.SERVICE.getType()  +  ".package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "Service",
                YamlConfigProperties.getStringProperty("file.generation.location.entity.package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName(),
                YamlConfigProperties.getStringProperty("file.generation.location.mapper.package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "Mapper"
        );
    }


}
