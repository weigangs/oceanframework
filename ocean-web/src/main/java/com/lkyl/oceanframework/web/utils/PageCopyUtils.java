package com.lkyl.oceanframework.web.utils;

import com.lkyl.oceanframework.web.model.PageList;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author nicholas
 * @date 2023/07/14 23:54
 */
public class PageCopyUtils {

    public static <S, T> List<T> convertProperties(PageList<S> list, Function<S, T> converter, BiConsumer<S, T> callback) {
        if (CollectionUtils.isEmpty(list)) {
            return empty();
        }


        return result(list, converter, callback);
    }

    private static <S, T> List<T> result(PageList<S> page, Function<S, T> converter, BiConsumer<S, T> callback) {
        PageList<T> result = copyPageWithoutData(page);
        result.setTotal(page.getTotal());

        Optional.ofNullable(converter).map(f -> result.addAll(page.stream().map(source -> {
                    T target = f.apply(source);
                    if (callback != null) {
                        callback.accept(source, target);
                    }
                    return target;
                }).toList()))
                .orElseThrow(() -> new RuntimeException("convert function is null"));

        return result;
    }

    private static <T> PageList<T> empty() {
        PageList<T> result = new PageList<>();
        result.setTotal(0);
        result.setPageNum(1);
        result.setPageSize(10);
        return result;
    }

    private static <S, T> PageList<T> copyPageWithoutData(PageList<S> page) {
        PageList<T> result = new PageList<>();
        result.setTotal(page.getTotal());
        result.setPageNum(page.getPageNum());
        result.setPageSize(page.getPageSize());
        return result;
    }
}
