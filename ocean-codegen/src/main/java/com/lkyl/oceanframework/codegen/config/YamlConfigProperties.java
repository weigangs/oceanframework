package com.lkyl.oceanframework.codegen.config;

import com.lkyl.oceanframework.codegen.factory.CacheFactory;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class YamlConfigProperties {

    private static final String PREFIX = "yml_config:";

    private YamlConfigProperties() {

    }

    public static void initConfigProperties(Properties properties) {
        properties.forEach((key, value) -> {
            CacheFactory.put(PREFIX + key.toString(), value);
        });

    }

    public static String getStringProperty(String propertyKey) {
        return getYmlProperty(propertyKey, String.class);
    }


    public static <T> T getYmlProperty(String propertyKey, Class<T> type) {
        if (Objects.isNull(propertyKey)) {
            return null;
        }
        String[] keyArr = propertyKey.split("\\.");
        Object nextObject = CacheFactory.get(PREFIX + keyArr[0]).orElse(null);
        for (int i = 1; i < keyArr.length; i++) {
            if (Objects.isNull(nextObject)) {
                return null;
            }
            if (nextObject instanceof Map) {
                nextObject = ((Map)nextObject).get(keyArr[i]);
            }
        }
        if (Objects.isNull(nextObject)) {
            return null;
        }
        return type.cast(nextObject);
    }
}
