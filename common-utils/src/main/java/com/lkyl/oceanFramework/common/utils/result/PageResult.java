package com.lkyl.oceanframework.common.utils.result;

import com.github.pagehelper.Page;
import com.lkyl.oceanframework.common.utils.constant.ResultConstant;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PageResult<T> extends CommonResult<Collection<T>> {

    private Collection<T> data;

    private long totalNum = 0;

    @Override
    public Collection<T> getData() {
        return data;
    }

    @Override
    public CommonResult<Collection<T>> setData(Collection<T> data) {
        this.data = data;
        return null;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public static <T> PageResult<T> page(Collection<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return empty();
        }

        if (!(list instanceof Page)) {
            throw new IllegalArgumentException("can not cast to Page.class");
        }

        return result((Page<T>) list);
    }

    private static<T> PageResult<T> result(Page<T> page) {
        PageResult<T> result =  empty();
        result.setData(page);
        result.setTotalNum(page.getTotal());
        return result;
    }

    public static<T> PageResult<T> empty() {
        PageResult<T> result = new PageResult<>();
        result.setCode(ResultConstant.OK_CODE);
        result.setMessage(ResultConstant.OK_MSG);
        return result;
    }

}