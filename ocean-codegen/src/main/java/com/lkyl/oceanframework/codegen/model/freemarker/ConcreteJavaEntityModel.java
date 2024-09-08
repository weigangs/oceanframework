package com.lkyl.oceanframework.codegen.model.freemarker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
public class ConcreteJavaEntityModel implements JavaEntityModel {

    private String entityName;

    private List<JavaFieldModel> fieldList;
}
