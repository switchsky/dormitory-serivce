package com.switchsky.web;

import com.switchsky.web.sys_user.entity.SysUser;
import com.switchsky.web.sys_user.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userTest {
    @Autowired
    SysUserService sysUserService;

    @Test
    public void testAddUser() {
        SysUser sysUser = new SysUser();
        sysUser.setRoleId(1L);
        sysUser.setUsername("xxl");
        sysUser.setPassword("1111");
        sysUser.setPhone("13305711918");
        sysUser.setSex("0");
        sysUser.setIsAdmin("1");
        sysUserService.add(sysUser);
    }
}
