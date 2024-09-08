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