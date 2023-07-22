package com.lkyl.oceanframework.common.utils.utils;

import com.github.pagehelper.Page;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author nicholas
 * @date 2023/07/14 23:54
 */
public class PageCopyUtils {

    public static <S, T> List<T> convertProperties(Collection<S> list, Function<S, T> converter, BiConsumer<S, T> callback) {
        if (CollectionUtils.isEmpty(list)) {
            return empty();
        }

        if (!(list instanceof Page)) {
            throw new IllegalArgumentException("can not cast to Page.class");
        }

        return result((Page<S>) list, converter, callback);
    }

    private static <S, T> List<T> result(Page<S> page, Function<S, T> converter, BiConsumer<S, T> callback) {
        Page<T> result = copyPageWithoutData(page);
        result.setTotal(page.getTotal());

        Optional.ofNullable(converter).map(f -> result.addAll(page.stream().map(source -> {
                    T target = f.apply(source);
                    if (callback != null) {
                        callback.accept(source, target);
                    }
                    return target;
                }).collect(Collectors.toList())))
                .orElseThrow(() -> new RuntimeException("convert function is null"));

        return result;
    }

    private static <T> Page<T> empty() {
        Page<T> result = new Page<>();
        result.setTotal(0);
        result.setPageNum(1);
        result.setPageSize(10);
        return result;
    }

    private static <S, T> Page<T> copyPageWithoutData(Page<S> page) {
        Page<T> result = new Page<>();
        result.setTotal(page.getTotal());
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }
}
