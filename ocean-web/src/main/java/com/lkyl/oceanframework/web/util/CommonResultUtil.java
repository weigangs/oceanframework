package com.lkyl.oceanframework.web.util;

import com.github.pagehelper.PageInfo;
import com.lkyl.oceanframework.common.utils.constant.CommonCode;
import com.lkyl.oceanframework.common.utils.constant.CommonResult;
import com.lkyl.oceanframework.common.utils.constant.PaginatedResult;
import org.springframework.http.ResponseEntity;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月21日 12:48
 */
public class CommonResultUtil {

    public static ResponseEntity<?> successMsg(String msg) {
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg(msg));
    }
    public static ResponseEntity<?> success(Object data) {
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg(CommonCode.SUCCESS_MSG)
                .setData(data));
    }

    public static ResponseEntity<?> success(String msg, Object data) {
        return ResponseEntity.ok(new CommonResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg(msg)
                .setData(data));
    }

    public static ResponseEntity<?> pagingSuccess(PageInfo pageInfo) {
        return ResponseEntity.ok(new PaginatedResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg(CommonCode.SUCCESS_MSG)
                .setData(pageInfo.getList())
                .setCurrentPage(pageInfo.getPageNum())
                .setTotalPage(pageInfo.getPages())
                .setTotalCount(pageInfo.getTotal()));
    }

    public static ResponseEntity<?> pagingSuccess(String msg, PageInfo pageInfo) {
        return ResponseEntity.ok(new PaginatedResult()
                .setCode(CommonCode.SUCCESS)
                .setMsg(msg)
                .setData(pageInfo.getList())
                .setCurrentPage(pageInfo.getPageNum())
                .setTotalPage(pageInfo.getPages())
                .setTotalCount(pageInfo.getTotal()));
    }
}
