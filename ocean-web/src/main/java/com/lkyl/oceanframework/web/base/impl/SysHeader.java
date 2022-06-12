package com.lkyl.oceanframework.web.base.impl;

import com.lkyl.oceanframework.web.base.Header;
import lombok.Data;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月21日 11:48
 */
@Data
public class SysHeader implements Header {
    String tenantId;

    @Override
    public String getTanentId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
