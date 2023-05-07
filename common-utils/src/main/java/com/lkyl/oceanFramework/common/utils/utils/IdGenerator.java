package com.lkyl.oceanframework.common.utils.utils;

import cn.hutool.core.lang.Snowflake;

/**
 * @author nicholas
 * @date 2023/05/07 17:35
 */
public class  IdGenerator {

    private IdGenerator() {
    }

    public static long next() {
        return SpringContextUtil.getBean(Snowflake.class).nextId();
    }

    public static String nextStr() {
        return SpringContextUtil.getBean(Snowflake.class).nextIdStr();
    }
}
