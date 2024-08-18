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
import com.lkyl.oceanframework.codegen.utils.StringUtils;

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
            "java.util.Objects",
            "java.util.Optional",
            "java.util.Collections",
            "org.mybatis.dynamic.sql.select.QueryExpressionDSL",
            "org.mybatis.dynamic.sql.select.SelectModel",
            "org.mybatis.dynamic.sql.update.render.UpdateStatementProvider",
            "org.mybatis.dynamic.sql.SqlBuilder",
            "org.mybatis.dynamic.sql.render.RenderingStrategies",
            "org.mybatis.dynamic.sql.select.render.SelectStatementProvider",
            "com.woniu.common.service.common.enums.CommonExceptionEnum",
            "com.lkyl.oceanframework.common.utils.exception.BusinessException",
            "com.lkyl.oceanframework.common.utils.page.PageArgs",
            "com.lkyl.oceanframework.common.utils.utils.PageCopyUtils",
            "org.apache.commons.lang.StringUtils"

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
        ServiceImplJavaEntityModel serviceImplJavaEntityModel = buildJavaModelFromRaw(context.getConcreteJavaEntityModel());
        result.setEntityModel(serviceImplJavaEntityModel);
        result.setImportList(new ArrayList<>());
        result.getImportList().addAll(DEFAULT_IMPORT_LIST);
        result.getImportList().addAll(getCommonImportList(context));
        result.getImportList().addAll(getCreationMethodImportList(context));
        result.getImportList().addAll(getUpdateMethodImportList(context));
        result.getImportList().addAll(getImplDetailMethodImportList(context));
        result.getImportList().addAll(getPageQueryMethodImportList(context));
        result.getImportList().addAll(getDynamicSupportImportList(context, serviceImplJavaEntityModel.getFieldList()));
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
                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.SERVICE.getType() + ".package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "Service",
                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.QUERY_COMPONENT.getType() + ".package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "QueryComponent",
                YamlConfigProperties.getStringProperty("file.generation.location.entity.package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName(),
                YamlConfigProperties.getStringProperty("file.generation.location.mapper.package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "Mapper"
        );
    }

    private List<String> getImplDetailMethodImportList(CodeGenContext codeGenContext) {
        ArrayList<String> resultList = new ArrayList<>(getDetailMethodImportList(codeGenContext));
        resultList.addAll(List.of(
                        YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.CONVERTER.getType() + ".package") +
                                "." +
                                codeGenContext.getConcreteJavaEntityModel().getEntityName() + "Converter"

                )
        );
        return resultList;
    }

    private List<String> getDynamicSupportImportList(CodeGenContext codeGenContext, List<JavaFieldModel> updateList) {
        String dynamicSupportClassName = "static " +
                YamlConfigProperties.getStringProperty("file.generation.location.mapper.package") +
                "." +
        codeGenContext.getConcreteJavaEntityModel().getEntityName() + "DynamicSqlSupport";
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add(dynamicSupportClassName +
                        "." +
                        StringUtils.decapitalize(codeGenContext.getConcreteJavaEntityModel().getEntityName()));

        updateList.forEach(e -> {
            resultList.add(
                    dynamicSupportClassName +
                    "." +
                    e.getFieldName());
        });

        return resultList;
    }


}
