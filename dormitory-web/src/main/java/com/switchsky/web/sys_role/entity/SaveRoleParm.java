package com.switchsky.web.sys_role.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class SaveRoleParm {
    //角色id
    private Long roleId;
    //菜单的id
    private List<Long> list;
}
