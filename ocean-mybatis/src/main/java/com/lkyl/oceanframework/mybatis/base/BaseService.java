package com.lkyl.oceanframework.mybatis.base;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BaseService<T, PK> {

    T getById(PK id);

    T getByParam(T param);

    Optional<T> get(PK id);

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
