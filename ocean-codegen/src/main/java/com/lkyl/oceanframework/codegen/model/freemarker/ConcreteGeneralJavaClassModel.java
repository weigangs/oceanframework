package com.lkyl.oceanframework.codegen.model.freemarker;

import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class ConcreteGeneralJavaClassModel implements GeneralJavaClassModel {

    private TempldateFileTypeEnum typeEnum;

    private JavaEntityModel entityModel;

    private String packageName;

    private List<String> importList;

    private String className;

    private String fileName;
}
