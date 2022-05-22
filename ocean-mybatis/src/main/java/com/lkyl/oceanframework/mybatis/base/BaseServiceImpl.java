package com.lkyl.oceanframework.mybatis.base;

import com.lkyl.oceanframework.common.utils.constant.MybatisConstant;
import com.lkyl.oceanframework.common.utils.utils.CollectionUtils;

import java.util.*;


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
    public int updateOnWhere(T t) {
        return baseDao.updateOnWhere(t);
    }

    @Override
    public int saveOrUpdateBatch(List<T> list) {
        return baseDao.saveOrUpdateBatch(list);
    }

    @Override
    public int remove(PK id) {
        return baseDao.remove(id);
    }

    @Override
    public int removeOnWhere(T t) {
        return baseDao.removeOnWhere(t);
    }

    @Override
    public int batchRemove(List<PK> ids) {
        return baseDao.batchRemove(ids);
    }
}
