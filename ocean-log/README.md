# oceanframework
# ocean-log

#### 介绍
日志解析组件

#### 
exmaple: 
==================================logback.xml
<!-- 不同业务日志记录到不同的文件 -->
    <appender name="businessLogAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>logs/business.log</File>
        <append>true</append>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>logs/common-service.%d.%i.log</fileNamePattern>
            <maxHistory>90</maxHistory>
            <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                <maxFileSize>10MB</maxFileSize>
            </timeBasedFileNamingAndTriggeringPolicy>
        </rollingPolicy>
        <encoder>
            <pattern>${LOG_PATTERN}</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <logger name="businessLog" additivity="false" level="INFO">
        <appender-ref ref="businessLogAppender"/>
    </logger>
===========================================starter
@EnableLogRecord(tenantId = "dev")
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
        log.info("========>island-common-service started!");
    }
}
============================================usage
@GetMapping("/test")
    @LogRecord(content = "第一次测试，方法参数为#{#zou}，使用自定义方法【firstTest】解析结果：#{@{firstTest(#zou)}}", bizNo = "001")
    public ResponseEntity<?> testLog(@RequestParam("zou") String zou) {
        return ResponseEntity.ok(zou);
    }

#### 安装教程

1.  mvn install

#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
