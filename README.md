# oceanframework

#### 介绍
统一框架版本，及特殊组件集成平台

1. common-auth(security module)
2. common-cache(provider some cache feature)
3. common-utils(base module for all modules)
4. ocean-geo(spacial module provider calculate longitude and altitude point)
5. ocean-security(old security module, has been replaced by common-auth)
6. ocean-web(provider global exception handle and so on...)
7. ocean-codegen(code generation module)

# common-auth

## 1. 模块简介(module introduce)
    该模块使用了spring-security框架来实现登录，鉴权等。
    this module use spring-security archive login, authentication and so on.
## 2. 具体实现类(concrete implementation class)
    com.lkyl.oceanframework.common.auth.token.TokenService.java
    com.lkyl.oceanframework.common.auth.filter.TokenCheckFilter.java
    当然还有一些配置类，具体实现可看代码，变量名称以及类名称可以直观了解意图

### 2.1 TokenService.java
    createJwtToken 用于业务代码判断完登录成功后，进行token的创建。storeUser 用来存储用存储userPricipal,
    refreshToken 用来刷新token时间，时间可配置，removeToken 移除token
    createJwtToken is used by creating token after determing that the login is successful. 
### 2.2 TokenCheckFilter.java
    具体实现鉴权的类，继承了OncePerRequestFilter，在doFilterInternal方法中，如果从请求头中获取到了token则
    将用户信息解析出来放入SecurityContext中，从而实现鉴权通过，否则返回错误码401
## 3. 以下为yaml配置文件属性
    ocean:
        security:
            oauth2:
                tokenKey: authToken
                token:
                    expireMinute: 30
                permittedUrls:
                    - /login
                    - /health
                    - /logout
                corsAllowedMethods:
                    - GET
                    - POST


# common-cache

## 1. 模块简介(module introduce)
    该模块使用了redis框架来实现缓存，正在持续开发中。
    this module use spring-security archive login, authentication and so on.
## 2. 具体实现类(concrete implementation class)
    com.lkyl.oceanframework.common.cache.util.RedisStoreUtils.java
    com.lkyl.oceanframework.common.cache.limit.aspect.LimitRestAspect.java
    当然还有一些配置类，具体实现可看代码，变量名称以及类名称可以直观了解意图

### 2.1 RedisStoreUtils.java
    getLock 通过luna脚本获取分布式锁 releaseLock 用于释放分布式锁,
    目前存在问题：没有redisValue锁会被其它线程错误释放
### 2.2 LimitRestAspect.java
    使用redis作限流，之前参考的某篇技术文档实现的，若有不足，可以pull request补充
## 3. 无配置项

# common-utils

## 1. 模块简介(module introduce)
    该模块集中了常用的一些组件，如分页组件，异常捕获组件，雪花算法，mapperstruct组件，request响应公共类等。
## 2. 具体实现类(concrete implementation class)
    com.lkyl.oceanframework.common.utils.page.PageSelectorAspect.java
    com.lkyl.oceanframework.common.utils.mapperstruct.base.BaseMapperConverter.java
    当然还有一些配置类，具体实现可看代码，变量名称以及类名称可以直观了解意图

### 2.1 PageSelectorAspect.java
    将注解（@PageSelector）添加到查数据库的方法上，方法上有PageArgs参数，就可以实现分页
### 2.2 BaseMapperConverter.java
    具体实现可参考island-common工程，在我的主页可查看，以最新的develop分支为准
## 3. 以下为yaml配置文件属性
    pagehelper:
        pageSizeZero: true
        helper-dialect: mysql
        reasonable: true
        support-methods-arguments: true
        params: count=countSql
    snowflake:
        workerId: 1
        datacenterId: 2

# ocean-codegen

## 1. 模块简介(module introduce)
    该模块使用了freemarker框架来实现类文件生成，使用mybatis-generator实现mapper及entity文件生成。
## 2. 具体实现类(concrete implementation class)
    com.lkyl.oceanframework.codegen.tools.OceanCodeGenerator.java
    com.lkyl.oceanframework.codegen.config.RootConfig.java
### 2.1 OceanCodeGenerator.java
    generateFiles 生成文件的方法，只需要传递一个config参数即可
### 2.2 RootConfig.java
    可以getInstance(传递一个配置文件名称)，缺省是：默认文件名称为[codeGenerator.yml]
## 3. 以下为yaml配置文件属性
```
tableName: tableName
#database:
database:
  url: jdbc:mysql://localhost:3306/dbname
  driverName: com.mysql.cj.jdbc.Driver
  username: name
  password: password

mybatis:
  generator:
    forceBigDecimals: true
    useJSR310Types: true
    style: dynamic  #哪种风格的entity, mapper
file:
  generation:
    # 如果有则生成代码，没有则不生成
    type:
#      - dto
#      - vo
#      - service
#      - serviceImpl
#      - controller
#      - converter
#      - queryComponent
      - mybatisBase
    location: #每个类型的文件都生成在哪个路径下，对应的包
      dto:
        package: com.xxx.dto
        targetProject: D:\xxx\xxx-api\src\main\java
      vo:
        package: com.xxx.vo
        targetProject: D:\xxx\xxx-api\src\main\java
      service:
        package: com.xxx.service
        targetProject: D:\xxx\xxx-service-impl\src\main\java
      serviceImpl:
        package: com.xxx.service.impl
        targetProject: D:\xxx\xxx-service-impl\src\main\java
      controller:
        package: com.xxx.controller
        targetProject: D:xxx\src\main\java
      converter:
        package: com.xxx.converter
        targetProject: D:xxx\src\main\java
      queryComponent:
        package: com.xxx.component
        targetProject: D:xxx\src\main\java
      entity:
        package: com.xxx.entity
        targetProject: D:xxx\src\main\java
      mapper:
        package: com.xxx.mapper
        targetProject: D:\xxx\src\main\java
    createDto: #生成DTO时需要排除哪些字段
      excludes:
        javaFields:
          - updatedBy
          - createdBy
          - id
          - isDeleted
          - revision
          - updatedTime
          - createdTime
          - tenantId
    updateDto: #生成DTO时需要排除哪些字段
      excludes:
        javaFields:
          - updatedBy
          - createdBy
          - isDeleted
          - revision
          - updatedTime
          - createdTime
          - tenantId
defaultValue:
  javaFields:
    id: IdGenerator.next()
    updatedBy: UserContext.getUser().getUserCode()
    createdBy: UserContext.getUser().getUserCode()
    isDeleted: YesOrNoEnum.NO.getCode()
    updatedTime: LocalDateTime.now()
    createdTime: LocalDateTime.now()
    tenantId: UserContext.getUser().getTenantId()
    revision: 0L


```
# ocean-geo

## 1. 模块简介(module introduce)
    该模块使用了geotools来实现两点（经纬度）之间距离的计算，正在持续开发中。
    this module use spring-security archive login, authentication and so on.
## 2. 具体实现类(concrete implementation class)
    com.lkyl.oceanframework.geo.util.Gcj02DistanceUtils.java
    com.lkyl.oceanframework.geo.util.CoordinateTransformUtils.java
    当然还有一些配置类，具体实现可看代码，变量名称以及类名称可以直观了解意图

### 2.1 Gcj02DistanceUtils.java
    calculateDistance 可计算出两点间的距离，单位：米
### 2.2 CoordinateTransformUtils.java
    gcjToWgs 将gcj02坐标转换成WGS84坐标
## 3. 无配置项

# 总结
    以上模块基本上满足开发需求
    如在使用过程中有任何疑问：请联系swg1024@gmail.com

#### for mybatis generator
动态sql也可以使用mapper
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
    

