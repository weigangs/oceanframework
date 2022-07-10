package com.lkyl.oceanframework.mybatis.base;

import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.MybatisConstant;
import com.lkyl.oceanframework.common.utils.exception.CommonException;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Supplier;


/**
 * @author nicholas
 */
public class BaseServiceImpl<T, PK> implements BaseService<T, PK> {

    private BaseDao<T, PK> baseDao;
    public void setBaseDao(BaseDao<T, PK> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public T getById(PK id) {
        return baseDao.get(id);
    }

    @Override
    public T getByParam(T param) {
        return baseDao.getByParam(param);
    }

    @Override
    public Optional<T> get(PK id) {
        return Optional.ofNullable(baseDao.get(id));
    }

    @Override
    public List<T> queryByIdList(Map<String, Object> map) {
        if(CollectionUtils.isNotEmpty(map)) {
            if(CollectionUtils.isEmpty((Collection<?>) map.get(MybatisConstant.ID_LIST))) {
                return new ArrayList<>(0);
            }
            return baseDao.queryByIdList(map);
        }

        return new ArrayList<>(0);
    }

    @Override
    public List<T> list(T param) {
        return baseDao.list(param);
    }

    @Override
    public int count(T param) {
        return baseDao.count(param);
    }

    @Override
    public int save(T t) {
        return baseDao.save(t);
    }

    @Override
    public int batchSave(List<T> list) {
        return baseDao.batchSave(list);
    }

    @Override
    public int updateById(T t) {
        return baseDao.updateById(t);
    }

    @Override
    public int updateOnWhere(T setter, T where) {
        return baseDao.updateOnWhere(setter, where);
    }

    @Override
    public int saveOrUpdateBatch(List<T> list) {
        return baseDao.saveOrUpdateBatch(list);
    }

    @Override
    public int removeById(PK id) {
        return baseDao.removeById(id);
    }

    @Override
    public int removeOnWhere(T t) {
        return baseDao.removeOnWhere(t);
    }

    @Override
    public int batchRemoveById(List<PK> ids) {
        return baseDao.batchRemoveById(ids);
    }

    @Override
    public int logicRemoveById(PK id) {
        return baseDao.logicRemoveById(id);
    }

    @Override
    public int commonSave(Object dto, Supplier<T> supplier)  {
        T saveEntity = supplier.get();
        BeanUtils.copyProperties(dto, saveEntity);
        final int count = save(saveEntity);
        if (count != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "新增失败!");
        }

        return count;
    }

    @Override
    public int commonUpdate(Object dto, Supplier<T> supplier) {
        T updateEntity = supplier.get();
        BeanUtils.copyProperties(dto, updateEntity);
        final int count = updateById(updateEntity);
        if(count != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "更新失败!");
        }

        return count;
    }

    @Override
    public int commonRemove(PK id) {
        final int count = removeById(id);
        if (count != 1) {
            throw new CommonException(CommonCode.EXCEPTION, "删除失败!");
        }

        return count;
    }
}
