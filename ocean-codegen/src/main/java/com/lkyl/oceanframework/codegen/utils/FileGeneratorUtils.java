package com.lkyl.oceanframework.codegen.utils;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.model.freemarker.ConcreteGeneralJavaClassModel;
import com.lkyl.oceanframework.codegen.model.freemarker.GeneralJavaClassModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGeneratorUtils {

    public static void writeFileWithFreemarker(CodeGenContext context, GeneralJavaClassModel classMode) throws TemplateException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(FileGeneratorUtils.class, "/templates/");
        Template template = cfg.getTemplate(classMode.getTypeEnum().getFileName());

        String fileWritePath =
                YamlConfigProperties.getStringProperty("file.generation.location." + classMode.getTypeEnum().getType() + ".targetProject") +
                File.separator +
                classMode.getPackageName().replace(".", File.separator) +
                File.separator +
                classMode.getFileName();

        File writeableFile = new File(fileWritePath);
        // 确保父目录存在
        File parentDir = writeableFile.getParentFile();
        createDirectoryIfNotExists(parentDir.getAbsolutePath());
        // 输出生成的代码到控制台
        FileWriter writer = new FileWriter(writeableFile);
        template.process(classMode, writer);
        System.out.println(fileWritePath + " write success!");

    }

    public static void createDirectoryIfNotExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();  // 递归创建所有必要的父目录
            System.out.println("Created directory: " + path);
        }
    }
}
