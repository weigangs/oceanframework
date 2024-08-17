package com.lkyl.oceanframework.codegen.resolver;

import com.lkyl.oceanframework.codegen.dom.FullyQualifiedJavaType;
import com.lkyl.oceanframework.codegen.model.DbTableColumn;

import java.util.Properties;

public interface JavaTypeResolver {

     void addConfigurationProperties(Properties properties);
    FullyQualifiedJavaType calculateJavaType(DbTableColumn introspectedColumn);
}
