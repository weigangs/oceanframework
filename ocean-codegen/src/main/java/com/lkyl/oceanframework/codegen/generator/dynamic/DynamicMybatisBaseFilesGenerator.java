package com.lkyl.oceanframework.codegen.generator.dynamic;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
import com.lkyl.oceanframework.codegen.generator.FileGenerator;
import com.lkyl.oceanframework.codegen.utils.FileGeneratorUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DynamicMybatisBaseFilesGenerator implements FileGenerator {
    @Override
    public TempldateFileTypeEnum getFileType() {
        return TempldateFileTypeEnum.MYBATIS_BASE;
    }

    @Override
    public void generateFile(CodeGenContext codeContext) {
// MyBatis Generator 配置
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        // 生成代码
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(buildMybatisConfig(codeContext), callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (String warning : warnings) {
            System.out.println(warning);
        }

    }

    private Configuration buildMybatisConfig(CodeGenContext codeContext) {
        // 创建配置对象
        Configuration config = new Configuration();

        // 创建 Context 对象并设置 targetRuntime
        Context mybatisContext = new Context(ModelType.FLAT);
        mybatisContext.setId("example");
        mybatisContext.setTargetRuntime("MyBatis3DynamicSql");

        // 设置属性
        mybatisContext.addProperty("javaFileEncoding", "UTF-8");

        // CommentGenerator 配置
        CommentGeneratorConfiguration commentGeneratorConfig = new CommentGeneratorConfiguration();
        commentGeneratorConfig.addProperty("suppressAllComments", "true");
        mybatisContext.setCommentGeneratorConfiguration(commentGeneratorConfig);

        // JDBC Connection 配置
        JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();
        jdbcConfig.setDriverClass(codeContext.getDatabaseConnModel().getDriver());
        jdbcConfig.setConnectionURL(codeContext.getDatabaseConnModel().getUrl());
        jdbcConfig.setUserId(codeContext.getDatabaseConnModel().getUsername());
        jdbcConfig.setPassword(codeContext.getDatabaseConnModel().getPassword());
        mybatisContext.setJdbcConnectionConfiguration(jdbcConfig);

        // Java Type Resolver 配置
        JavaTypeResolverConfiguration javaTypeResolverConfig = new JavaTypeResolverConfiguration();
        javaTypeResolverConfig.addProperty("forceBigDecimals", Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis.generator.forceBigDecimals", Boolean.class))
                .orElse(Boolean.FALSE).toString());
        javaTypeResolverConfig.addProperty("useJSR310Types", Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis.generator.useJSR310Types", Boolean.class))
                .orElse(Boolean.FALSE).toString());
        mybatisContext.setJavaTypeResolverConfiguration(javaTypeResolverConfig);

        // Java Model Generator 配置
        mybatisContext.setJavaModelGeneratorConfiguration(buildJavaModelConfig());

        // Java Client Generator 配置
        mybatisContext.setJavaClientGeneratorConfiguration(buildJavaMapperConfig());

        // Table 配置
        TableConfiguration tableConfig = new TableConfiguration(mybatisContext);
        tableConfig.setTableName(codeContext.getTableName());
        mybatisContext.addTableConfiguration(tableConfig);

        // 添加 Context 到配置中
        config.addContext(mybatisContext);
        return config;
    }

    private JavaModelGeneratorConfiguration buildJavaModelConfig() {
        // Java Model Generator 配置
        JavaModelGeneratorConfiguration javaModelGeneratorConfig = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfig.setTargetPackage(YamlConfigProperties.getStringProperty("file.generation.location.entity.package"));
        String srcMainJavaDirPathForEntity =  YamlConfigProperties.getStringProperty("file.generation.location.entity.targetProject");
        FileGeneratorUtils.createDirectoryIfNotExists(srcMainJavaDirPathForEntity);
        javaModelGeneratorConfig.setTargetProject(srcMainJavaDirPathForEntity);
        javaModelGeneratorConfig.addProperty("enableSubPackages", "false");
        javaModelGeneratorConfig.addProperty("trimStrings", "true");
        return javaModelGeneratorConfig;
    }

    private JavaClientGeneratorConfiguration buildJavaMapperConfig() {
        JavaClientGeneratorConfiguration javaClientGeneratorConfig = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfig.setTargetPackage(YamlConfigProperties.getStringProperty("file.generation.location.mapper.package"));
        String srcMainJavaDirPathForMapper = YamlConfigProperties.getStringProperty("file.generation.location.mapper.targetProject");
        FileGeneratorUtils.createDirectoryIfNotExists(srcMainJavaDirPathForMapper);
        javaClientGeneratorConfig.setTargetProject(srcMainJavaDirPathForMapper);
        javaClientGeneratorConfig.setConfigurationType("ANNOTATEDMAPPER");
        javaClientGeneratorConfig.addProperty("enableSubPackages", "false");
        return javaClientGeneratorConfig;
    }
}
