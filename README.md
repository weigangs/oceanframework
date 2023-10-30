# oceanframework

#### 介绍
统一框架版本，及特殊组件集成平台

1. common-auth(security module)
2. common-cache(provider some cache feature)
3. common-utils(base module for all modules)
4. ocean-geo(spacial module provider calculate longitude and altitude point)
5. ocean-security(old security module, has been replaced by common-auth)
6. ocean-web(provider global exception handle and so on...)

#### for mybatis generator
there is two approach to generate entity and mapper
one is use those config below, you can use a xml file for sql 
    
    <dependency>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-core</artifactId>
    <version>1.4.2</version>

    </dependency><dependency>
        <groupId>com.itfsw</groupId>
        <artifactId>mybatis-generator-plugin</artifactId>
        <version>1.3.10</version>
    </dependency>

    <!-- mybatis-generator 自动代码插件 -->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.7</version>
                <configuration>
                    <!-- 配置文件 -->
                    <configurationFile>src/main/resources/generatorConfig.xml</configurationFile>
                    <!-- 允许移动和修改 -->
                    <verbose>true</verbose>
                    <overwrite>true</overwrite>
                </configuration>
                <dependencies>
                    <!-- jdbc 依赖 -->
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>8.0.28</version>
                    </dependency>
                    <dependency>
                        <groupId>com.itfsw</groupId>
                        <artifactId>mybatis-generator-plugin</artifactId>
                        <version>1.3.10</version>
                    </dependency>
                </dependencies>
            </plugin>
another way to use dynamic sql by follow those config, by default it will not generate xml file for sql
        
    <!-- mybatis generator -->
    <dependency>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-core</artifactId>
    </dependency>
    
    generatorConfig.xml like this:

    <?xml version="1.0" encoding="UTF-8"?>
            <!DOCTYPE generatorConfiguration
                    PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
                    "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
    <generatorConfiguration>
    <!--Mybatis Generator目前有5种运行模式，分别为：MyBatis3DynamicSql、MyBatis3Kotlin、MyBatis3、MyBatis3Simple、MyBatis3DynamicSqlV1。-->
    <context id="example" targetRuntime="MyBatis3DynamicSql">
        <property name="javaFileEncoding" value="UTF-8" />
        <commentGenerator>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="true" />
        </commentGenerator>
    <!--    <jdbcConnection
                connectionURL="jdbc:mysql://127.0.0.1:3306/test?tinyInt1isBit=false&amp;useUnicode=true&amp;characterEncoding=utf-8&amp;serverTimezone=Asia/Shanghai&amp;nullCatalogMeansCurrent=true"
                driverClass="com.mysql.jdbc.Driver"
                userId="root"
                password="root"/>-->
    
        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://localhost:3306/test" userId="root"
                        password="root" />
    
        <javaTypeResolver>
            <!-- 默认false，把JDBC DECIMAL 和 NUMERIC 类型解析为 Integer， 为 true时把JDBC DECIMAL
            和 NUMERIC 类型解析为java.math.BigDecimal -->
            <property name="forceBigDecimals" value="false" />
            <!--是否试用jdk8时间类-->
            <property name="useJSR310Types" value="true"/>
        </javaTypeResolver>
    
        <!-- targetProject:生成PO类的位置 -->
        <javaModelGenerator
                targetPackage="com.xxx.entity"
                targetProject="D:\xxx\src\main\java">
            <!-- enableSubPackages:是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="false" />
            <!-- 从数据库返回的值被清理前后的空格 -->
            <property name="trimStrings" value="true" />
        </javaModelGenerator>
    
    
        <!-- targetPackage：mapper接口生成的位置 -->
        <javaClientGenerator
                targetPackage="com.xxx.mapper"
                targetProject="D:xxx\src\main\java" type="ANNOTATEDMAPPER">
            <!-- enableSubPackages:是否让schema作为包的后缀 -->
            <property name="enableSubPackages" value="false" />
        </javaClientGenerator>
    
        <!--生成全部表tableName设为%-->
        <!-- 指定数据库表 -->
        <table tableName="xxx" >
        </table>
    
        <!-- 指定数据库表
          schema:数据库的schema,可以使用SQL通配符匹配。如果设置了该值，生成SQL的表名会变成如schema.tableName的形式。
          domainObjectName:生成对象的基本名称。如果没有指定，MBG会自动根据表名来生成名称。
          -->
        <!--<table schema="" tableName="" domainObjectName=""/>-->
    </context>
    </generatorConfiguration>
    

