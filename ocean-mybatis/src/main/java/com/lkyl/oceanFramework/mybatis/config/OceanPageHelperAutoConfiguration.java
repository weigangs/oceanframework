//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lkyl.oceanframework.mybatis.config;

import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Configuration
@ConditionalOnBean({SqlSessionFactory.class})
@EnableConfigurationProperties({PageHelperProperties.class})
@AutoConfigureAfter({MybatisAutoConfiguration.class})
public class OceanPageHelperAutoConfiguration {
    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;
    @Autowired
    private PageHelperProperties properties;

    public OceanPageHelperAutoConfiguration() {
    }

    // 注释掉，不然在springboot2.6.x 产生循环注入
//    @Bean
//    @ConfigurationProperties(
//        prefix = "pagehelper"
//    )
//    public Properties pageHelperProperties() {
//        return new Properties();
//    }

    @PostConstruct
    public void addPageInterceptor() {
        PageInterceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
//        properties.putAll(this.pageHelperProperties());
        properties.putAll(this.properties.getProperties());
        interceptor.setProperties(properties);
        Iterator var3 = this.sqlSessionFactoryList.iterator();

        while(var3.hasNext()) {
            SqlSessionFactory sqlSessionFactory = (SqlSessionFactory)var3.next();
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }

    }
}
