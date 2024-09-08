package com.lkyl.oceanframework.codegen.generator;

import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;

public interface FileGenerator {

    String JAVA_LANG_PACKAGE_PREFIX = "java.lang";

    TempldateFileTypeEnum getFileType();

    void generateFile(CodeGenContext context);
}
