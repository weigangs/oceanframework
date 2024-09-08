package com.lkyl.oceanframework.codegen.generator;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.constants.CodeGenConstants;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteGeneralJavaClassModel;
import com.lkyl.oceanframework.codegen.utils.FileGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class ServiceFileGenerator  implements FileGenerator {

    private final static List<String> DEFAULT_IMPORT_LIST = List.of(
            "java.util.List",
            "com.lkyl.oceanframework.common.utils.page.PageArgs"
    );

    private final static String CLASS_NAME_SUFFIX = "Service";

    @Override
    public TempldateFileTypeEnum getFileType() {
        return TempldateFileTypeEnum.SERVICE;
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
        result.setImportList( new ArrayList<>());
        result.getImportList().addAll(DEFAULT_IMPORT_LIST);
        result.getImportList().addAll(getCreationMethodImportList(context));
        result.getImportList().addAll(getUpdateMethodImportList(context));
        result.getImportList().addAll(getDetailMethodImportList(context));
        result.getImportList().addAll(getPageQueryMethodImportList(context));
        result.setTypeEnum(getFileType());
        result.setClassName(context.getConcreteJavaEntityModel().getEntityName() + CLASS_NAME_SUFFIX);
        result.setFileName(result.getClassName() + CodeGenConstants.JAVA_FILE_NAME_EXT);
        return result;
    }

    protected List<String> getCreationMethodImportList(CodeGenContext context) {
        return List.of(

                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.CREATE_DTO.getType()  +  ".package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "CreateDTO"
        );
    }
    protected List<String> getUpdateMethodImportList(CodeGenContext context) {
        return List.of(

                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.UPDATE_DTO.getType()  +  ".package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "UpdateDTO"
        );
    }

    protected List<String> getDetailMethodImportList(CodeGenContext context) {
        return List.of(

                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.DETAIL_VO.getType()  +  ".package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "DetailVO"
        );
    }

    protected List<String> getPageQueryMethodImportList(CodeGenContext context) {
        return List.of(

                YamlConfigProperties.getStringProperty("file.generation.location." + TempldateFileTypeEnum.PAGE_QUERY_DTO.getType()  +  ".package") +
                        "." +
                        context.getConcreteJavaEntityModel().getEntityName() + "PageQueryDTO"
        );
    }
}
