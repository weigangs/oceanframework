package com.lkyl.oceanframework.codegen.generator;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.constants.CodeGenConstants;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteGeneralJavaClassModel;
import com.lkyl.oceanframework.codegen.utils.FileGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class ConverterFileGenerator implements FileGenerator {

    private final static List<String> DEFAULT_IMPORT_LIST = List.of(
            "com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter",
            "org.mapstruct.Mapper",
//            "org.mapstruct.Mapping",
//            "org.mapstruct.Mappings",
            "org.mapstruct.NullValueCheckStrategy",
            "org.mapstruct.NullValuePropertyMappingStrategy",
            "org.mapstruct.factory.Mappers"
    );

    private final static String CLASS_NAME_SUFFIX = "Converter";

    @Override
    public TempldateFileTypeEnum getFileType() {
        return TempldateFileTypeEnum.CONVERTER;
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
        result.setImportList(new ArrayList<>(List.of(

                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.DETAIL_VO.getType()  +  ".package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "DetailVO",
                YamlConfigProperties.getStringProperty("file.generation.location.entity.package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName()
        )));
        result.getImportList().addAll(DEFAULT_IMPORT_LIST);
        result.setTypeEnum(getFileType());
        result.setClassName(context.getConcreteJavaEntityModel().getEntityName() + CLASS_NAME_SUFFIX);
        result.setFileName(result.getClassName() + CodeGenConstants.JAVA_FILE_NAME_EXT);
        return result;
    }


}
