package com.lkyl.oceanframework.security.security;

import lombok.Data;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月19日 23:55
 */
@Data
public class OceanUserPrincipal implements Serializable {

    private Long userId;
    private Long deptId;
    private String userName;
    private String nickName;
    private String userType;
    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
    private String password;
    private String status;
    private String delFlag;
    private String loginIp;
    private Date loginDate;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String remark;
    private String tenantId;
    private List<String> roles;
}
