package com.lkyl.oceanframework.mybatis.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T, PK> {
    T get(PK id);

    T getByParam(T param);

    List<T> list(T param);

    int count(T param);

    int save(T t);

    int batchSave(List<T> list);

    int updateById(T t);

    int updateOnWhere(T t);

    int saveOrUpdateBatch(List<T> list);

    int remove(PK id);

    int removeOnWhere(T t);

    int batchRemove(List<PK> ids);
}
