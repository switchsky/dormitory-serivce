package com.switchsky.web.sys_user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.switchsky.utils.ReadTxt;
import com.switchsky.utils.ResultUtils;
import com.switchsky.utils.ResultVo;
import com.switchsky.web.sys_role.entity.SysRole;
import com.switchsky.web.sys_role.service.SysRoleService;
import com.switchsky.web.sys_user.entity.*;
import com.switchsky.web.sys_user.service.SysUserService;
import com.switchsky.web.sys_user_role.entity.SysUserRole;
import com.switchsky.web.sys_user_role.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    //新增用户
    @PostMapping
    public ResultVo addUser(@RequestBody SysUser user) {
        //判断账户是否被占用
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, user.getUsername());
        SysUser one = sysUserService.getOne(query);
        if (one != null) {
            return ResultUtils.error("账户被占用!");
        }
        user.setIsAdmin("0");
        user.setCreateTime(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        //入库处理
        sysUserService.add(user);
        return ResultUtils.success("新增用户成功!");
    }

    //编辑用户
    @PutMapping
    public ResultVo editUser(@RequestBody SysUser user) {
        //判断账户是否被占用
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUsername, user.getUsername());
        SysUser one = sysUserService.getOne(query);
        if (one != null && one.getUserId() != user.getUserId()) {
            return ResultUtils.error("账户被占用!");
        }
        user.setUpdateTime(new Date());
        //更新处理
        sysUserService.edit(user);
        return ResultUtils.success("编辑用户成功!");
    }

    //删除用户
    @DeleteMapping("/{userId}")
    public ResultVo deleteUser(@PathVariable("userId") Long userId) {
        boolean remove = sysUserService.removeById(userId);
        if (remove) {
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }

    //列表查询
    @GetMapping("/list")
    public ResultVo getList(PageParm parm) {
        IPage<SysUser> list = sysUserService.list(parm);
        //密码不显示
        list.getRecords().stream().forEach(item -> {
            item.setPassword("");
        });
        return ResultUtils.success("查询成功", list);
    }

    //查询角色列表
    @GetMapping("/roleList")
    public ResultVo getRoleList(){
        List<SysRole> list = sysRoleService.list();
        return ResultUtils.success("查询成功",list);
    }

    //查询用户对应的角色
    @GetMapping("/getRoleByUserId")
    public ResultVo getRoleByUserId(Long userId){
        QueryWrapper<SysUserRole> query = new QueryWrapper<>();
        query.lambda().eq(SysUserRole::getUserId,userId);
        SysUserRole one = sysUserRoleService.getOne(query);
        return ResultUtils.success("查询成功",one);
    }

    @GetMapping("/getUserInfo")
    public ResultVo getUserInfo(UserInfoParam param){
        if (param.getUserType()==null||param.getUserId()==null) {
            return ResultUtils.error("查询个人信息失败");
        }
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUserId, param.getUserId());
        SysUser one = sysUserService.getOne(query);
        if(one == null) return ResultUtils.error("用户不存在");
        String img = "";
        if(one.getImg()!=null) {
            img = ReadTxt.readFile(one.getImg());
        }
        UserInfoV0 userInfoV0 = new UserInfoV0();
        userInfoV0.setAge(one.getAge());
        userInfoV0.setImg(img);
        userInfoV0.setPhone(one.getPhone());
        userInfoV0.setHobby(one.getHobby());
        userInfoV0.setEmail(one.getEmail());
        userInfoV0.setSex(one.getSex());
        userInfoV0.setSignature(one.getSignature());
        userInfoV0.setNickName(one.getNickName());
        userInfoV0.setUsername(one.getUsername());
        return ResultUtils.success("ok",userInfoV0);
    }
    @PutMapping("/updatePerson")
    public ResultVo updateUserInfo(@RequestBody UserEditInfoParam param) throws IOException {
        if (param.getUserType()==null||param.getUserId()==null) {
            return ResultUtils.error("查询个人信息失败");
        }
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUserId, param.getUserId());
        SysUser user = sysUserService.getOne(query);
        user.setAge(param.getAge());
        user.setUserId(Long.valueOf(param.getUserId()));
        user.setEmail(param.getEmail());
        user.setHobby(param.getHobby());
        //图片处理
        String img = param.getImg();
        if (img == null||img.trim().equals(""))
            user.setImg(img);
        else {
            String url = "D:/img/"+param.getUserId()+".txt";
            user.setImg(ReadTxt.writeFile(url,img));
        }
        user.setNickName(param.getNickName());
        user.setPhone(param.getPhone());
        user.setSignature(param.getSignature());
        user.setSex(param.getSex());
        user.setUpdateTime(new Date());
        //更新处理
        sysUserService.edit(user);
        return ResultUtils.success("更新成功");
    }

    @PutMapping("/changePassword")
    public ResultVo changePassword(@RequestBody ChangePasswordParam param) throws IOException {
        if (param.getUserId()==null) {
            return ResultUtils.error("查询个人信息失败");
        }
        QueryWrapper<SysUser> query = new QueryWrapper<>();
        query.lambda().eq(SysUser::getUserId, param.getUserId());
        SysUser user = sysUserService.getOne(query);
        String password = DigestUtils.md5DigestAsHex(param.getOldpassword().getBytes());
        if(!password.equals(user.getPassword())) return ResultUtils.error("原密码错误");
        String newPassword = DigestUtils.md5DigestAsHex(param.getNewpassword().getBytes());
        user.setPassword(newPassword);
        //更新处理
        sysUserService.edit(user);
        return ResultUtils.success("更新成功");
    }
}
