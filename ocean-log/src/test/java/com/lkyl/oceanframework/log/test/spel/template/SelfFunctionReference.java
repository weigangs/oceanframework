package com.lkyl.oceanframework.log.test.spel.template;

import com.lkyl.oceanframework.log.factory.ParseFunctionFactory;
import com.lkyl.oceanframework.log.function.IParseFunction;
import org.springframework.expression.*;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.SpelMessage;
import org.springframework.expression.spel.ast.SpelNodeImpl;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.util.*;

import static org.springframework.expression.spel.SpelMessage.FUNCTION_NOT_DEFINED;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年06月28日 23:00
 */
public class SelfFunctionReference extends SpelNodeImpl {

    private String selfFunctionName;

    @Nullable
    private volatile IParseFunction iParseFunction;

    private Object [] args;


    public SelfFunctionReference(String selfFunctionName, int startPos, int endPos, SpelNodeImpl... arguments) {
        super(startPos, endPos, arguments);
        this.selfFunctionName = selfFunctionName;
    }

//    @Override
//    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
//        try {
//            Object beanFactory = state.getEvaluationContext().getBeanResolver().resolve(state.getEvaluationContext(), "parseFunctionFactory");
//            if(beanFactory != null && beanFactory instanceof ParseFunctionFactory) {
//                IParseFunction iParseFunction = ((ParseFunctionFactory) beanFactory).getFunction(this.selfFunctionName);
//                if(iParseFunction != null) {
//                    Object [] args = getArguments(state);
//                    if(args.length <= 0) {
//                        throw new SpelEvaluationException(SpelMessage.INCORRECT_NUMBER_OF_ARGUMENTS_TO_FUNCTION, args.length, ReflectionUtils.findMethod(iParseFunction.getClass(), "apply").getParameterCount());
//                    }
//                    this.iParseFunction = iParseFunction;
//                    this.args = args;
//                    Object resultValue = iParseFunction.apply((String)args[0]);
//                    return new TypedValue(resultValue);
//                }
//            }
//            throw new SpelEvaluationException(FUNCTION_NOT_DEFINED, this.selfFunctionName);
//        } catch (EvaluationException e) {
//            throw e;
//        } catch (AccessException e) {
//            throw new EvaluationException("resolve beanFactory error!");
//        }
//
//
//    }

    @Override
    public TypedValue getValueInternal(ExpressionState state) throws EvaluationException {
        try {
            Map<String, IParseFunction> functionMap = (Map<String, IParseFunction>)state.getEvaluationContext().lookupVariable("functionMap");
            if(functionMap != null ) {
                IParseFunction iParseFunction = functionMap.get(this.selfFunctionName);
                if(iParseFunction != null) {
                    Object [] args = getArguments(state);
                    if(args.length <= 0) {
                        throw new SpelEvaluationException(SpelMessage.INCORRECT_NUMBER_OF_ARGUMENTS_TO_FUNCTION, args.length, ReflectionUtils.findMethod(iParseFunction.getClass(), "apply").getParameterCount());
                    }
                    this.iParseFunction = iParseFunction;
                    this.args = args;
                    Object resultValue = iParseFunction.apply((String)args[0]);
                    return new TypedValue(resultValue);
                }
            }
            throw new SpelEvaluationException(FUNCTION_NOT_DEFINED, this.selfFunctionName);
        } catch (EvaluationException e) {
            throw e;
        }


    }


    @Override
    public String toStringAST() {
        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (int i = 0; i < getChildCount(); i++) {
            sj.add(getChild(i).toStringAST());
        }
        return "@{" + this.selfFunctionName + sj.toString() + "}";
    }

    /**
     * Compute the arguments to the function, they are the children of this expression node.
     * @return an array of argument values for the function call
     */
    private Object[] getArguments(ExpressionState state) throws EvaluationException {
        // Compute arguments to the function
        Object[] arguments = new Object[getChildCount()];
        for (int i = 0; i < arguments.length; i++) {
            arguments[i] = this.children[i].getValueInternal(state).getValue();
        }
        return arguments;
    }

    @Override
    public boolean isCompilable() {

        return this.iParseFunction != null && args.length > 0;
    }

//    @Override
//    public void generateCode(MethodVisitor mv, CodeFlow cf) {
//        Assert.state(this.iParseFunction != null, "No selfFunction handle");
//
//        mv.visitLdcInsn(this.iParseFunction.apply());
//        cf.pushDescriptor(this.exitTypeDescriptor);
//    }

}
