package com.lkyl.oceanframework.web.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Closeable;
import java.util.ArrayList;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageList<E> extends ArrayList<E> implements Closeable {

    private int pageNum;
    private int pageSize;
    private long startRow;
    private long endRow;
    private long total;
    private int pages;

    @Override
    public void close()  {

    }
}
