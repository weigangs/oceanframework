package com.lkyl.oceanframework.codegen.model.freemarker;

import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;

import java.util.List;

public interface GeneralJavaClassModel {
     TempldateFileTypeEnum getTypeEnum();

     JavaEntityModel getEntityModel();

     String getPackageName();

     List<String> getImportList();

     String getClassName();

     String getFileName();
}
