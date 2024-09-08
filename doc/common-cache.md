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