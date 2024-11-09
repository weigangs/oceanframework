package com.lkyl.oceanframework.codegen.generator;

import com.lkyl.oceanframework.codegen.config.YamlConfigProperties;
import com.lkyl.oceanframework.codegen.context.CodeGenContext;
import com.lkyl.oceanframework.codegen.enums.TempldateFileTypeEnum;
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
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MybatisBaseFilesGenerator implements FileGenerator {
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
            myBatisGenerator = new MyBatisGenerator(buildDefaultConfig(codeContext), callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (String warning : warnings) {
            System.out.println(warning);
        }

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

    private Configuration buildDefaultConfig(CodeGenContext codeContext) {

        Context context = new Context(ModelType.FLAT);
        context.setId("example");
        context.setTargetRuntime("MyBatis3");

        // Set properties
        context.addProperty("javaFileEncoding", "UTF-8");
        context.addProperty("javaFormatter", "org.mybatis.generator.api.dom.DefaultJavaFormatter");
        context.addProperty("xmlFormatter", "org.mybatis.generator.api.dom.DefaultXmlFormatter");

        // Plugins
        List<String> plugins = Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis3.plugins", List.class))
                .orElseGet(Collections::emptyList);
        plugins.forEach(plugin -> {
            PluginConfiguration serializablePlugin = new PluginConfiguration();
            serializablePlugin.setConfigurationType(plugin);
            context.addPluginConfiguration(serializablePlugin);
        });



        // Comment generator
        CommentGeneratorConfiguration commentConfig = new CommentGeneratorConfiguration();
        commentConfig.addProperty("suppressAllComments", Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis3.suppressAllComments", Boolean.class))
                .orElse(Boolean.FALSE).toString());
        commentConfig.addProperty("suppressDate", Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis3.suppressDate", Boolean.class))
                .orElse(Boolean.FALSE).toString());
        commentConfig.addProperty("addRemarkComments", Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis3.addRemarkComments", Boolean.class))
                .orElse(Boolean.FALSE).toString());
        context.setCommentGeneratorConfiguration(commentConfig);

        // JDBC connection
        // JDBC Connection 配置
        JDBCConnectionConfiguration jdbcConfig = new JDBCConnectionConfiguration();
        jdbcConfig.setDriverClass(codeContext.getDatabaseConnModel().getDriver());
        jdbcConfig.setConnectionURL(codeContext.getDatabaseConnModel().getUrl());
        jdbcConfig.setUserId(codeContext.getDatabaseConnModel().getUsername());
        jdbcConfig.setPassword(codeContext.getDatabaseConnModel().getPassword());
        context.setJdbcConnectionConfiguration(jdbcConfig);

        // Java Type Resolver
        // Java Type Resolver 配置
        JavaTypeResolverConfiguration javaTypeResolverConfig = new JavaTypeResolverConfiguration();
        javaTypeResolverConfig.addProperty("forceBigDecimals", Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis.generator.forceBigDecimals", Boolean.class))
                .orElse(Boolean.FALSE).toString());
        javaTypeResolverConfig.addProperty("useJSR310Types", Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis.generator.useJSR310Types", Boolean.class))
                .orElse(Boolean.FALSE).toString());
        context.setJavaTypeResolverConfiguration(javaTypeResolverConfig);

        // Java Model Generator
        context.setJavaModelGeneratorConfiguration(buildJavaModelConfig());

        // SQL Map Generator
        SqlMapGeneratorConfiguration sqlMapGenConfig = new SqlMapGeneratorConfiguration();
        sqlMapGenConfig.setTargetPackage(YamlConfigProperties.getStringProperty("file.generation.location.xmlMapper.package"));
        String xmlMapperPath = YamlConfigProperties.getStringProperty("file.generation.location.xmlMapper.targetProject");
        sqlMapGenConfig.setTargetProject(xmlMapperPath);
        context.setSqlMapGeneratorConfiguration(sqlMapGenConfig);

        // Java Client Generator
        JavaClientGeneratorConfiguration clientGenConfig = new JavaClientGeneratorConfiguration();
        clientGenConfig.setConfigurationType("XMLMAPPER");
        clientGenConfig.setTargetPackage(YamlConfigProperties.getStringProperty("file.generation.location.mapper.package"));
        String srcMainJavaDirPathForMapper = YamlConfigProperties.getStringProperty("file.generation.location.mapper.targetProject");
        clientGenConfig.setTargetProject(srcMainJavaDirPathForMapper);
        context.setJavaClientGeneratorConfiguration(clientGenConfig);

        // Table configuration
        TableConfiguration tableConfig = new TableConfiguration(context);
        tableConfig.setSchema("");
        tableConfig.setTableName(codeContext.getTableName());
        tableConfig.setCountByExampleStatementEnabled(true);
        tableConfig.setDeleteByExampleStatementEnabled(true);
        tableConfig.setSelectByExampleStatementEnabled(true);
        tableConfig.setUpdateByExampleStatementEnabled(true);
        context.addTableConfiguration(tableConfig);

        Configuration config = new Configuration();
        config.addContext(context);
        return config;
    }
}
