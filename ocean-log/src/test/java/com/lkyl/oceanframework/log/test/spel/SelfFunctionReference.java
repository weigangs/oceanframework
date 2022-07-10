package com.lkyl.oceanframework.log.test.spel;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.ExpressionState;
import org.springframework.expression.spel.ast.SpelNodeImpl;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年06月28日 22:32
 */
public class SelfFunctionReference extends SpelNodeImpl {
    private String functionName;
    public SelfFunctionReference(int startPos, int endPos, SpelNodeImpl... operands) {
        super(startPos, endPos, operands);
    }

    @Override
    public TypedValue getValueInternal(ExpressionState expressionState) throws EvaluationException {
        return null;
    }

    @Override
    public String toStringAST() {
        return null;
    }
}
