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