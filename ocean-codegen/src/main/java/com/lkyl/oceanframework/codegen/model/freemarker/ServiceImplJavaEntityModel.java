package com.lkyl.oceanframework.codegen.model.freemarker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
@AllArgsConstructor
@ToString
@Getter
public class ServiceImplJavaEntityModel implements JavaEntityModel{
    private String entityName;

    private List<JavaFieldModel> fieldList;

    private List<JavaFieldModel> createFieldList;

    private List<JavaFieldModel> updateFieldList;
}
