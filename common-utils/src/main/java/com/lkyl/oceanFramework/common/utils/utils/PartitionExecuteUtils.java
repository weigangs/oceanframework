package com.lkyl.oceanframework.common.utils.utils;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

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
}
