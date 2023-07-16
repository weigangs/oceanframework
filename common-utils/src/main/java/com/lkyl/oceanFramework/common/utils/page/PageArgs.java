package com.lkyl.oceanframework.common.utils.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author nicholas
 * @date 2023/05/28 13:10
 */

@AllArgsConstructor
@Data
@ToString
public class PageArgs implements Serializable {

    private Integer pageNum;

    private Integer pageSize;

}
