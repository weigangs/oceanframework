package com.lkyl.oceanframework.common.utils.utils;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class PartitionExecuteUtils {
    public static <T, R> List<R> queryDbPartition(
            List<T> allList, Function<List<T>,
            List<R>> executeFunc,
            int partitionSize
    ) {
        if (CollectionUtils.isEmpty(allList)) {
            return Collections.emptyList();
        }
        if (partitionSize <= 0) {
            partitionSize = allList.size();
        }
        List<R> resultList = new ArrayList<>();
        Lists.partition(allList, partitionSize).forEach(part -> {
            resultList.addAll(executeFunc.apply(part));
        });
        return resultList;
    }

    public static <T> int updateDbPartition(
            List<T> allList, ToIntFunction<List<T>> executeFunc,
            int partitionSize
    ) {
        if (CollectionUtils.isEmpty(allList)) {
            return 0;
        }
        if (partitionSize <= 0) {
            partitionSize = allList.size();
        }
        int resultCount = 0;
        for (List<T> part : Lists.partition(allList, partitionSize)) {
            resultCount += executeFunc.applyAsInt(part);
        }
        return resultCount;
    }
}
