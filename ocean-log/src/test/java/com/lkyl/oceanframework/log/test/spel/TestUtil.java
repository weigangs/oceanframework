package com.lkyl.oceanframework.log.test.spel;


import com.lkyl.oceanframework.common.utils.utils.ObjectUtils;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年06月06日 22:54
 */
public class TestUtil {

    public static String toUpperCase(String str) {
        return ObjectUtils.isNotEmpty(str) ? str.toUpperCase() : null;
    }

    public String getName(String name) {
        return "name is " + name;
    }
}
