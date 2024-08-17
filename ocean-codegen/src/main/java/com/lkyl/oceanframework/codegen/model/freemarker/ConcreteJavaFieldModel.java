package com.lkyl.oceanframework.codegen.model.freemarker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class ConcreteJavaFieldModel implements JavaFieldModel {

    private String javaFieldType;
    private String javaFullyFieldType;

    private String fieldName;

    private boolean nullable;

    private String defaultValue;

    private String remark;
}
