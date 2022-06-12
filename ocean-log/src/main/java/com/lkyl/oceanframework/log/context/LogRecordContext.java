package com.lkyl.oceanframework.log.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author nicholas
 */
public class LogRecordContext {

    private static final InheritableThreadLocal<Stack<Map<String, Object>>> VARIABLE_MAP_THREAD_LOCAL = new InheritableThreadLocal<>();
   //其他省略....

    public static Map<String, Object> getVariables() {
        return VARIABLE_MAP_THREAD_LOCAL.get().firstElement();
    }

    public static void putEmptySpan() {
        Stack<Map<String, Object>> stack = null;
        if(VARIABLE_MAP_THREAD_LOCAL.get() == null ) {
            stack = new Stack<>();
            stack.push(new HashMap<>());
            VARIABLE_MAP_THREAD_LOCAL.set(stack);
        }else{
            VARIABLE_MAP_THREAD_LOCAL.get().push(new HashMap<>());
        }

    }

    public static void clear() {
        VARIABLE_MAP_THREAD_LOCAL.get().pop();
    }
}
