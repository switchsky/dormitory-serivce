package com.switchsky.web.sys_role_menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.switchsky.web.sys_role_menu.entity.SysRoleMenu;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    void saveRoleMenu(Long roleId, List<Long> menuIds);
}
