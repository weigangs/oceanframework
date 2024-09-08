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
