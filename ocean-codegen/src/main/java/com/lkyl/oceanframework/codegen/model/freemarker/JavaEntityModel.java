package com.lkyl.oceanframework.codegen.model.freemarker;

import java.util.List;

public interface JavaEntityModel {

     String getEntityName();

     List<JavaFieldModel> getFieldList();
}
