package com.lkyl.oceanframework.codegen.context;

import com.lkyl.oceanframework.codegen.model.DatabaseConnModel;
import com.lkyl.oceanframework.codegen.model.DbTableMetadata;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteJavaEntityModel;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CodeGenContext {

    private String tableName;

    private String projectPath;

    private DbTableMetadata dbTableMetadata;

    private ConcreteJavaEntityModel concreteJavaEntityModel;

    private DatabaseConnModel databaseConnModel;

}
