package com.lkyl.oceanframework.common.utils.utils;


import java.util.Collection;
import java.util.Map;

public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection){
        if(null == collection)
            return true;
        if(collection.size() <= 0){
            return true;
        }
        if(collection.isEmpty())
            return true;
        return false;
    }

    public static boolean isEmpty(Map map){
        if(null == map)
            return true;
        if(map.size() <= 0){
            return true;
        }
        if(map.isEmpty())
            return true;
        return false;
    }

}
