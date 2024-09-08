package com.lkyl.oceanframework.codegen.config;

import com.lkyl.oceanframework.codegen.factory.JavaTypeResolverFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

public class RootConfig {

    private String configFileName = "codeGenerator.yml";

    private RootConfig(String configFileName) {
        if (Objects.isNull(configFileName) || configFileName.trim().isEmpty()) {
            return;
        }
        this.configFileName = configFileName;
    }

    private static RootConfig _instance;


    public static RootConfig getInstance(String configFileName) {
        if (Objects.isNull(_instance)) {
            synchronized (RootConfig.class) {
                if (Objects.isNull(_instance)) {
                    _instance = new RootConfig(configFileName);
                }
            }
        }
        return _instance;
    }

    public void parseYamlConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(configFileName);
        YamlConfigProperties.initConfigProperties(yaml.loadAs(inputStream, Properties.class)) ;
    }

    public void processAfterRamlConfigLoaded() {

        configJavaResolver();
    }


    private void configJavaResolver() {
        Properties properties = new Properties();
        Boolean forceBigDecimals =
                Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis.generator.forceBigDecimals", Boolean.class))
                        .orElse(Boolean.FALSE);
        Boolean useJSR310Types =
                Optional.ofNullable(YamlConfigProperties.getYmlProperty("mybatis.generator.useJSR310Types", Boolean.class))
                        .orElse(Boolean.FALSE);
        properties.put("forceBigDecimals", forceBigDecimals);
        properties.put("useJSR310Types", useJSR310Types);
        JavaTypeResolverFactory.getJavaTypeResolver().addConfigurationProperties(properties);
    }

}
