package com.lkyl.oceanframework.codegen.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class CacheFactory {

    private CacheFactory() {

    }

    private static final Map<Object, Object> CACHE_MAP = new HashMap<>(50);


    public static void putAll(Map<Object, Object> map) {
        if (Objects.nonNull(map)) {
            CACHE_MAP.putAll(map);
        }
    }

    public static <T> Optional<T> get(Object key) {
        return get(key, null);
    }

    public static <T> Optional<T> get(Object key, Supplier<T> supplyIfNone) {
        Object result = CACHE_MAP.get(key);
        if (Objects.nonNull(result)) {
            return (Optional<T>) Optional.of(result);
        }
        if (Objects.nonNull(supplyIfNone)) {
            result = supplyIfNone.get();
        }
        if (Objects.isNull(result)) {
            return Optional.empty();
        }
        CACHE_MAP.put(key, result);
        return (Optional<T>) Optional.of(result);
    }


    public static <T> T put(Object key, T value) {
        return (T) CACHE_MAP.put(key, value);
    }
}
