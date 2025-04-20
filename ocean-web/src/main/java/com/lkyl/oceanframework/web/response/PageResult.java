package com.lkyl.oceanframework.web.response;

import com.lkyl.oceanframework.web.model.PageList;
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

    public static <T> PageResult<T> page(PageList<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new PageResult<>();
        }


        return result((list));
    }

    private static <T> PageResult<T> result(PageList<T> page) {
        PageResult<T> result = new PageResult<>();
        result.setData(page);
        result.setTotalNum(page.getTotal());
        return result;
    }

}