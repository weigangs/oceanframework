package com.lkyl.oceanframework.mybatis.base;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public interface BaseService<T, PK> {

    T getById(PK id);

    T getByParam(T param);

    Optional<T> get(PK id);

    List<T> queryByIdList(Map<String, Object> map);

    List<T> list(T param);

    int count(T param);

    int save(T t);

    int batchSave(List<T> list);

    int updateById(T t);

    int updateOnWhere(T setter, T where);

    int saveOrUpdateBatch(List<T> list);

    int removeById(PK id);

    int removeOnWhere(T t);

    int batchRemoveById(List<PK> ids);

    /**
     * 根据主键ID 逻辑删除
     * @param id   主键ID
     * @return  逻辑删除成功个数
     */
    int logicRemoveById(PK id);

    /**
     * 新增失败会抛异常
     * @param dto
     * @return
     */
    int commonSave(Object dto, Supplier<T> supplier);

    /**
     * 修改失败会抛异常
     * @param dto
     * @return
     */
    int commonUpdate(Object dto, Supplier<T> supplier);

    /**
     * 修改失败会抛异常
     * @param id
     * @return
     */
    int commonRemove(PK id);
}
