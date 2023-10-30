package com.lkyl.oceanframework.common.utils.principal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author nicholas
 * @date 2023/05/15 20:52
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserPrincipal implements Serializable {

    private String userId;
    private String userCode;
    private String userName;
    private String openId;
    private List<String> roleList;

    private List<String> permissionList;

}
