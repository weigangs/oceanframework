package com.lkyl.oceanframework.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.function.Supplier;

public class BeanUtils {
    public static Map<String, Object> beanToMap(Object bean){
        Map<String, Object> map = new HashMap<>();

        try {

            // 获取JavaBean的描述器
            BeanInfo b = Introspector.getBeanInfo(bean.getClass(), Object.class);

            // 获取属性描述器
            PropertyDescriptor[] pds = b.getPropertyDescriptors();

            // 对属性迭代
            for (PropertyDescriptor pd : pds) {

                // 属性名称
                String propertyName = pd.getName();

                // 属性值,用getter方法获取
                Method m = pd.getReadMethod();

                // 用对象执行getter方法获得属性值
                Object properValue = m.invoke(bean);

                // 把属性名-属性值 存到Map中
                map.put(propertyName, properValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }



    public static boolean isGtZero(Object pObj) {
        return !isLeZeroOrNull(pObj);
    }

    public static JSONObject bean2JsonObject(Object object) {
        JSONObject jsonObject = new JSONObject();
        if(isEmpty(object)) {
            return jsonObject;
        }
        int mode;
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if(fields != null){
            for(Field field : fields){
                mode = field.getModifiers();
                if(Modifier.isFinal(mode) || Modifier.isStatic(mode)){
                    continue;
                }
                field.setAccessible(true);
                try {
                    if(isNotEmpty(field.get(object))){
                        jsonObject.put(field.getName(), field.get(object).toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }

    public static <T> T json2Bean(JSONObject source, Supplier<T> supplier){
        try {
            Type fieldType;
            Class<?> fieldClazzType;
            //字段类型 static 还是 final
            int fieldMode;
            Object sourceValue;
            Object newObj = supplier.get();
            Field [] fields = newObj.getClass().getDeclaredFields();
            if(fields != null){
                for(Field field : fields){
                    fieldMode = field.getModifiers();
                    if(Modifier.isFinal(fieldMode) || Modifier.isStatic(fieldMode)){
                        continue;
                    }
                    sourceValue = source.get(field.getName());
                    if(!isEmpty(sourceValue)){
                        fieldType = field.getGenericType();
                        field.setAccessible(true);
                        if(fieldType instanceof Class<?>){
                            fieldClazzType = (Class<?>)fieldType;
                            if(String.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToString(sourceValue));
                            }else if(BigDecimal.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToBigDecimal(sourceValue));
                            }else if(Float.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToFloat(sourceValue));
                            }else if(Double.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToDouble(sourceValue));
                            }else if(Date.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToDate(sourceValue));
                            }else if(List.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToList(sourceValue));
                            }else if(Timestamp.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToTimestamp(sourceValue));
                            }else if(Boolean.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToBoolean(sourceValue));
                            }else if(Long.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToLong(sourceValue));
                            }else if(Byte.class.isAssignableFrom(fieldClazzType)){
                                field.set(newObj, convertToByte(sourceValue));
                            }
                        }

                    }
                }
            }
            return (T)newObj;
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isGeZero(Object pObj) {
        if (pObj == null) {
            return false;
        }

        if (pObj instanceof Number) {
            if (convertToInteger(pObj) >= 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean isLeZeroOrNull(Object pObj) {
        if (pObj == null) {
            return true;
        }

        if (pObj instanceof Number) {
            if (convertToInteger(pObj) <= 0) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotEmpty(Object pObj) {
        return !isEmpty(pObj);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }

        if (pObj == "") {
            return true;
        }

        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    public static String convertToString(Object obj, String defaultVal) {
        try {
            return (obj != null) ? String.valueOf(obj) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static String convertToString(Object obj) {
        return convertToString(obj, "");
    }

    public static Boolean convertToBoolean(Object obj, boolean defaultVal) {
        try {
            return (obj != null) ? Boolean.parseBoolean(convertToString(obj)) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Boolean convertToBoolean(Object obj) {
        return convertToBoolean(obj, false);
    }

    public static Integer convertToInteger(Object obj, Integer defaultVal) {
        try {
            return (obj != null) ? Integer.parseInt(convertToString(obj)) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Integer convertToInteger(Object obj) {
        return convertToInteger(obj, 0);
    }

    public static Short convertToShort(Object obj, Short defaultVal) {
        try {
            return (obj != null) ? Short.parseShort(convertToString(obj)) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Short convertToShort(Object obj) {
        return convertToShort(obj, (short) 0);
    }

    public static Byte convertToByte(Object obj, Byte defaultVal) {
        try {
            return (obj != null) ? Byte.parseByte(convertToString(obj)) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Byte convertToByte(Object obj) {
        return convertToByte(obj, (byte) 0);
    }

    public static Float convertToFloat(Object obj, Float defaultVal) {
        try {
            return (obj != null) ? Float.parseFloat(convertToString(obj)) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Float convertToFloat(Object obj) {
        return convertToFloat(obj, 0F);
    }

    public static Long convertToLong(Object obj, Long defaultVal) {
        try {
            return (obj != null) ? Long.parseLong(convertToString(obj)) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Long convertToLong(Object obj) {
        return convertToLong(obj, 0L);
    }

    public static Double convertToDouble(Object obj, Double defaultVal) {
        try {
            return (obj != null) ? Double.parseDouble(convertToString(obj)) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static Double convertToDouble(Object obj) {
        return convertToDouble(obj, 0D);
    }

    public static BigDecimal convertToBigDecimal(Object obj, BigDecimal defaultVal) {
        try {
            return (obj != null) ? BigDecimal.valueOf(convertToDouble(obj)) : defaultVal;
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static BigDecimal convertToBigDecimal(Object obj) {
        return convertToBigDecimal(obj, BigDecimal.ZERO);
    }

    public static Date convertToDate(Object obj, Date defaultVal) {
        return obj == null ? defaultVal : DateUtil.strToDate(convertToString(obj), null);
    }

    public static Date convertToDate(Object obj) {
        return convertToDate(obj, null);
    }

    public static Date convertToDatetime(Object obj, Date defaultVal) {
        return obj == null ? defaultVal : DateUtil.strToDateTime(convertToString(obj));
    }

    public static Date convertToDatetime(Object obj) {
        return convertToDatetime(obj, null);
    }

    public static Timestamp convertToTimestamp(Object obj, Timestamp defaultVal) {
        return obj == null ? defaultVal : Timestamp.valueOf(convertToString(obj));
    }

    public static Timestamp convertToTimestamp(Object obj) {
        return convertToTimestamp(obj, null);
    }

    public static String convertToLike(String obj) {
        return "%" + obj + "%";
    }

    @SuppressWarnings("unchecked")
    public static List<Object> convertToList(Object object) {
        List<Object> result = new ArrayList<>();
        if (isNotEmpty(object) && object instanceof List) {
            result = (List<Object>) object;
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return sb.toString().substring(0, sb.toString().length() - 1);
    }

}
