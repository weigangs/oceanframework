package com.lkyl.oceanframework.log.interceptor;

import com.lkyl.oceanframework.log.context.LogRecordContext;
import com.lkyl.oceanframework.log.enums.LogRecordEnum;
import com.lkyl.oceanframework.log.evaluator.LogRecordExpressionEvaluator;
import com.lkyl.oceanframework.log.options.LogRecordOps;
import com.lkyl.oceanframework.log.parser.LogRecordOperationSource;
import com.lkyl.oceanframework.log.result.MethodExecuteResult;
import com.lkyl.oceanframework.log.service.IFunctionService;
import com.lkyl.oceanframework.log.service.ILogRecordService;
import com.lkyl.oceanframework.log.service.IOperatorGetService;
import com.lkyl.oceanframework.log.spelExt.ops.SelfFunctionReference;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.common.CompositeStringExpression;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.springframework.aop.support.AopUtils.getTargetClass;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月28日 13:50
 */
@Slf4j
public class LogRecordInterceptor implements MethodInterceptor , BeanFactoryAware {

    private LogRecordExpressionEvaluator expressionEvaluator;

    private LogRecordOperationSource logRecordOperationSource;

    @Resource
    private IFunctionService iFunctionService;

    private BeanFactory beanFactory;

    private String tenantId;

    @Resource
    private ILogRecordService iLogRecordService;

    @Resource
    private IOperatorGetService iOperatorGetService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        // 记录日志
        return execute(invocation, invocation.getThis(), method, invocation.getArguments());
    }

    private Object execute(MethodInvocation invoker, Object target, Method method, Object[] args) throws Throwable {
        Class<?> targetClass = getTargetClass(target);
        AnnotatedElementKey annotatedElementKey = new AnnotatedElementKey(method, targetClass);
        LogRecordContext.putEmptySpan();
        expressionEvaluator = new LogRecordExpressionEvaluator();
        EvaluationContext evaluationContext = expressionEvaluator.createEvaluationContext(method, args, target,
                null, null, beanFactory);
        Object ret = null;
        MethodExecuteResult methodExecuteResult = new MethodExecuteResult(true, null, "");

        Collection<LogRecordOps> operations = new ArrayList<>();
        Map<String, String> functionNameAndReturnMap = new HashMap<>();
        try {
            operations = logRecordOperationSource.computeLogRecordOperations(method, targetClass);
            List<Expression> selfFunExp = getBeforeExecuteFunctionTemplate(operations);
            //业务逻辑执行前的自定义函数解析
            functionNameAndReturnMap = processBeforeExecuteFunctionTemplate(selfFunExp, annotatedElementKey, evaluationContext);
        } catch (Exception e) {
            log.error("log record parse before function exception", e);
        }
        try {
            ret = invoker.proceed();
        } catch (Exception e) {
            methodExecuteResult = new MethodExecuteResult(false, e, e.getMessage());
        }
        try {
            if (!CollectionUtils.isEmpty(operations)) {
                recordExecute(annotatedElementKey, ret, operations, methodExecuteResult.isSuccess(), methodExecuteResult.getErrorMsg(),
                        functionNameAndReturnMap, evaluationContext);
            }
        } catch (Exception t) {
            //记录日志错误不要影响业务
            log.error("log record parse exception", t);
        } finally {
            LogRecordContext.clear();
        }
        if (methodExecuteResult.throwable != null) {
            throw methodExecuteResult.throwable;
        }
        return ret;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void  setLogRecordOperationSource(LogRecordOperationSource logRecordOperationSource) {
        this.logRecordOperationSource = logRecordOperationSource;
    }

    public void setFunctionService(IFunctionService iFunctionService) {
        this.iFunctionService = iFunctionService;
    }

    //记录日志
    private void recordExecute(AnnotatedElementKey annotatedElementKey, Object ret, Collection<LogRecordOps> operations,
                               boolean isSuccess, String errorMsg, Map<String, String> functionNameAndReturnMap,
                               EvaluationContext evaluationContext) {
        evaluationContext.setVariable("_ret", ret);
        if (!isSuccess) {
            evaluationContext.setVariable("_errorMsg", errorMsg);
        }
        //get operator name
        String userName;
        Optional<LogRecordOps> operator = operations.stream().takeWhile(ops -> StringUtils.equals(LogRecordEnum.OPERATOR.getAttrName(),
                ops.getLogRecordEnum().getAttrName())).findFirst();
        if(operator.isPresent()) {
            userName = this.expressionEvaluator.parseExpression(operator.get().getExpressString(), annotatedElementKey, evaluationContext);
        } else {
            userName = iOperatorGetService.getUser().getOperatorName();
        }
        //content
        StringBuilder contentStr = new StringBuilder(userName);
        Optional<LogRecordOps> content = operations.stream().takeWhile(ops -> StringUtils.equals(LogRecordEnum.CONTENT.getAttrName(),
                ops.getLogRecordEnum().getAttrName())).findFirst();
        if (!content.isPresent()) {
           throw new RuntimeException("parse @LogRecord content error, content is blank");
        }
         if (content.get().getExpression() instanceof CompositeStringExpression) {
             Stream.of(((CompositeStringExpression) content.get().getExpression()).getExpressions()).forEach(exp -> {
                 if (exp instanceof LiteralExpression) {
                     contentStr.append(((LiteralExpression) exp).getValue());
                 }
                 else {
                     String cachedValue = null;
                     if(((SpelExpression) exp).getAST() instanceof SelfFunctionReference ) {
                         cachedValue = functionNameAndReturnMap.
                                 get(((SelfFunctionReference) ((SpelExpression) exp).getAST()).getSelfFunctionName());
                     }
                     contentStr.append(cachedValue == null ? expressionEvaluator.parseExpression(exp.getExpressionString(),
                             annotatedElementKey, evaluationContext) : cachedValue);
                 }
             });
         } else {
             contentStr.append(expressionEvaluator.parseExpression(content.get().getExpressString(), annotatedElementKey, evaluationContext));
         }
        Optional<LogRecordOps> bizNo = operations.stream().takeWhile(ops -> StringUtils.equals(LogRecordEnum.BIZ_NO.getAttrName(),
                ops.getLogRecordEnum().getAttrName())).findFirst();
         if(bizNo.isPresent()) {
             contentStr.append(expressionEvaluator.parseExpression(bizNo.get().getExpressString(), annotatedElementKey, evaluationContext));
         }


//        LogRecord logRecord = new LogRecord(Logger.getLogger(LogRecordInterceptor.class.getName()).getLevel(), contentStr.toString());
        iLogRecordService.record(contentStr.toString());
    }

    /**
     * 目前支持第一层自定义函数提取
     * @param operations    注解中的content， operator，bizNo封装类
     * @return  预执行的自定义函数对应的expression
     */
    private List<Expression> getBeforeExecuteFunctionTemplate(Collection<LogRecordOps> operations) {
        List<Expression> selfFunExeBeforeExp = new ArrayList<>();
        operations.stream().filter(ops-> (ops.getExpression() instanceof CompositeStringExpression)
                || (ops.getExpression() instanceof SpelExpression)).map(LogRecordOps::getExpression).forEach(exp -> {
            if (exp instanceof CompositeStringExpression) {
                Stream.of(((CompositeStringExpression) exp).getExpressions()).forEach(subItem->{
                    if((subItem instanceof SpelExpression)
                            && (((SpelExpression) subItem).getAST() instanceof SelfFunctionReference)) {
                        selfFunExeBeforeExp.add(subItem);
                    }
                });
            } else if(((SpelExpression)exp).getAST() instanceof SelfFunctionReference){
                selfFunExeBeforeExp.add(exp);
            }
        });
        return selfFunExeBeforeExp;
    }

    private Map<String, String> processBeforeExecuteFunctionTemplate(List<Expression> selfFunExp,
                                                                     AnnotatedElementKey annotatedElementKey,
                                                                     EvaluationContext evaluationContext) {
        Map<String, String> selfNameValueMap = new HashMap<>(3);
        selfFunExp.stream().forEach(exp->{
            String funName = ((SelfFunctionReference)(((SpelExpression)exp).getAST())).getSelfFunctionName();
            if (iFunctionService.beforeFunction(funName)) {
                selfNameValueMap.put(funName,
                        expressionEvaluator.parseExpression(exp.getExpressionString(), annotatedElementKey, evaluationContext));
            }
        });
        return selfNameValueMap;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


}
