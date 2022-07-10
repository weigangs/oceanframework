package com.lkyl.oceanframework.mybatis.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @param <T> 实体类DO Class type
 * @param <PK>  实体类DO 主键字段 Class type
 * @author nicholas
 */
public interface BaseDao<T, PK> {
    /**
     * 根据ID获取一个实体类
     * @param id
     * @return
     */
    T get(PK id);

    /**
     * 根据实体类字段作为where条件，select one
     * @param param 实体类DO
     * @return  实体类
     */
    T getByParam(T param);

    /**
     * 根据where条件 查询列表
     * @param map  where 条件参数MAP
     * @return  实体类列表
     */
    List<T> queryByIdList(Map<String, Object> map);

    /**
     * 根据实体类中的字段 作为where条件 查询实体类列表
     * @param param     实体类DO
     * @return  实体类列表
     */
    List<T> list(T param);

    /**
     * 根据实体类字段作为 where条件 count查询到的结果个数
     * @param param 实体类DO
     * @return  结果集个数
     */
    int count(T param);

    /**
     * 插入一条记录
     * @param t 实体类DO
     * @return  成功入库的个数
     */
    int save(T t);

    /**
     *  批量插入多条记录
     * @param list  待入库的实体类DO列表
     * @return  成功入库的个数
     */
    int batchSave(List<T> list);

    /**
     * 根据主键ID 更新对应记录
     * @param t 实体类DO （包含主键ID字段）
     * @return 更新成功个数
     */
    int updateById(T t);

    /**
     * 根据where实体类字段做为where条件，setter作为set条件更新记录
     * @param setter setter实体类字段作为更新时set条件
     * @param where where实体类字段作为where条件的筛选项
     * @return  更新成功个数
     */
    int updateOnWhere(@Param("setter") T setter,@Param("where") T where);

    /**
     *  批量入库，若存在主键重复则更新
     * @param list  实体类DO列表
     * @return  插入或更新记录个数
     */
    int saveOrUpdateBatch(List<T> list);

    /**
     * 根据主键ID物理删除
     * @param id   实体类DO的 主键ID
     * @return  返回成功删除个数
     */
    int removeById(PK id);

    /**
     * 根据实体类字段作为where条件，进行物理删除
     * @param t 实体类DO
     * @return  返回删除成功个数
     */
    int removeOnWhere(T t);

    /**
     * 根据主键ID，批量物理删除
     * @param ids 主键ID列表
     * @return  删除成功个数
     */
    int batchRemoveById(List<PK> ids);

    /**
     * 根据主键ID 逻辑删除
     * @param id   主键ID
     * @return  逻辑删除成功个数
     */
    int logicRemoveById(PK id);
}
