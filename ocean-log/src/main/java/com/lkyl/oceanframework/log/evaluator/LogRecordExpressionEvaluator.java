package com.lkyl.oceanframework.log.evaluator;

import com.lkyl.oceanframework.log.context.LogRecordEvaluationContext;
import com.lkyl.oceanframework.log.spelExt.LogRecordCachedExpressionEvaluator;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author nicholas
 */
public class LogRecordExpressionEvaluator extends LogRecordCachedExpressionEvaluator {

    private Map<ExpressionKey, Expression> expressionCache = new ConcurrentHashMap<>(64);

    private final Map<AnnotatedElementKey, Method> targetMethodCache = new ConcurrentHashMap<>(64);

    public String parseExpression(String conditionExpression, AnnotatedElementKey methodKey, EvaluationContext evalContext) {
        return getExpression(this.expressionCache, methodKey, conditionExpression).getValue(evalContext, String.class);
    }

    public EvaluationContext createEvaluationContext(Method method, Object[] args, Object targetClass, Object ret, String errorMsg, BeanFactory beanFactory) {
        Method targetMethod = getTargetMethod(targetClass.getClass(), method);
        return new LogRecordEvaluationContext(targetClass, targetMethod, args, new DefaultParameterNameDiscoverer(), ret, errorMsg, beanFactory);
    }

    private Method getTargetMethod(Class<?> targetClass, Method method) {
        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, targetClass);
        Method targetMethod = this.targetMethodCache.get(methodKey);
        if (targetMethod == null) {
            targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
            if (targetMethod == null) {
                targetMethod = method;
            }
            this.targetMethodCache.put(methodKey, targetMethod);
        }
        return targetMethod;
    }

}
