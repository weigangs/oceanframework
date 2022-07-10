package com.lkyl.oceanframework.web.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年04月21日 23:49
 */


@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev")
public class OceanWebSwaggerConfig implements FactoryBean<OceanWebSwaggerConfig> {
    private static Logger log = LoggerFactory.getLogger(OceanWebSwaggerConfig.class);

    @Value("${spring.swagger.basePackage}")
    private String basePackage;

    @Bean
    @ConditionalOnMissingBean(Docket.class)
    public Docket createRestApi() {
        log.info("进入到swagger的配置中");
        return new Docket(DocumentationType.SWAGGER_2)
                // 指定构建api文档的详细信息的方法：apiInfo()
                .apiInfo(apiInfo())
                .select()
                // 指定要生成api接口的包路径，这里把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建api文档的详细信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("OceanWeb 接口总览")
                // 设置接口描述
                .description("Swagger接口")
                // 设置联系方式
                .contact(new Contact("swagger","http://localhost:8081/","123456@qq.com"))
                // 设置版本
                .version("1.0")
                // 构建
                .build();
    }

    @Override
    public OceanWebSwaggerConfig getObject() throws Exception {
        return this;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }
}
