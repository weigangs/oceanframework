package com.lkyl.oceanframework.codegen.model.freemarker;

public interface JavaFieldModel {

     String getJavaFieldType();

     String getJavaFullyFieldType();

     String getFieldName();

     boolean isNullable();

     String getDefaultValue();

     String getRemark();
}
