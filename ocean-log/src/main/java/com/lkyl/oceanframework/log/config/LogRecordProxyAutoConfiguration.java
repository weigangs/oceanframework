package com.lkyl.oceanframework.log.config;

import com.lkyl.oceanframework.log.adviser.BeanFactoryLogRecordAdvisor;
import com.lkyl.oceanframework.log.annotation.EnableLogRecord;
import com.lkyl.oceanframework.log.factory.ParseFunctionFactory;
import com.lkyl.oceanframework.log.function.IParseFunction;
import com.lkyl.oceanframework.log.function.impl.DefaultParseFunction;
import com.lkyl.oceanframework.log.interceptor.LogRecordInterceptor;
import com.lkyl.oceanframework.log.parser.LogRecordOperationSource;
import com.lkyl.oceanframework.log.service.IFunctionService;
import com.lkyl.oceanframework.log.service.ILogRecordService;
import com.lkyl.oceanframework.log.service.IOperatorGetService;
import com.lkyl.oceanframework.log.service.impl.DefaultFunctionServiceImpl;
import com.lkyl.oceanframework.log.service.impl.DefaultLogRecordServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

@Configuration
@Slf4j
public class LogRecordProxyAutoConfiguration implements ImportAware {

  private AnnotationAttributes enableLogRecord;


  @Bean
  @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
  public LogRecordOperationSource logRecordOperationSource() {
    return new LogRecordOperationSource();
  }

  @Bean
  @ConditionalOnMissingBean(IFunctionService.class)
  public IFunctionService functionService(ParseFunctionFactory parseFunctionFactory) {
    return new DefaultFunctionServiceImpl(parseFunctionFactory);
  }

  @Bean
  public ParseFunctionFactory parseFunctionFactory(@Autowired List<IParseFunction> parseFunctions) {
    return new ParseFunctionFactory(parseFunctions);
  }

  @Bean
  @ConditionalOnMissingBean(IParseFunction.class)
  public DefaultParseFunction parseFunction() {
    return new DefaultParseFunction();
  }


  @Bean
  @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
  public BeanFactoryLogRecordAdvisor logRecordAdvisor(IFunctionService functionService) {
    BeanFactoryLogRecordAdvisor advisor =
            new BeanFactoryLogRecordAdvisor();
    advisor.setLogRecordOperationSource(logRecordOperationSource());
    advisor.setAdvice(logRecordInterceptor(functionService));
    return advisor;
  }

  @Bean
  @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
  public LogRecordInterceptor logRecordInterceptor(IFunctionService functionService) {
    LogRecordInterceptor interceptor = new LogRecordInterceptor();
    interceptor.setLogRecordOperationSource(logRecordOperationSource());
    interceptor.setTenantId(enableLogRecord.getString("tenantId"));
    interceptor.setFunctionService(functionService);
    return interceptor;
  }

  @Bean
  @ConditionalOnMissingBean(ILogRecordService.class)
  @Role(BeanDefinition.ROLE_APPLICATION)
  public ILogRecordService recordService() {
    return new DefaultLogRecordServiceImpl();
  }

  @Override
  public void setImportMetadata(AnnotationMetadata importMetadata) {
    this.enableLogRecord = AnnotationAttributes.fromMap(
            importMetadata.getAnnotationAttributes(EnableLogRecord.class.getName(), false));
    if (this.enableLogRecord == null) {
      log.info("@EnableCaching is not present on importing class");
    }
  }
}
