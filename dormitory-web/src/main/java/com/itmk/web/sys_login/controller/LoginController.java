package com.itmk.web.sys_login.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.itmk.config.jwt.JwtUtils;
import com.itmk.config.redis.RedisUtil;
import com.itmk.utils.CodeUtil;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.school_student.entity.SchoolStudent;
import com.itmk.web.school_student.service.SchoolStudentService;
import com.itmk.web.sys_login.entity.CaptchaResult;
import com.itmk.web.sys_login.entity.LoginParm;
import com.itmk.web.sys_login.entity.LoginResult;
import com.itmk.web.sys_login.entity.UserInfo;
import com.itmk.web.sys_menu.entity.MakeTree;
import com.itmk.web.sys_menu.entity.RouterVO;
import com.itmk.web.sys_menu.entity.SysMenu;
import com.itmk.web.sys_menu.service.SysMenuService;
import com.itmk.web.sys_user.entity.SysUser;
import com.itmk.web.sys_user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Author thf
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private SchoolStudentService schoolStudentService;
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private RedisUtil redisUtil;
    //验证码获取

    @GetMapping("/verifyCode")
    public ResultVo verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        Map<String,Object> map = CodeUtil.generateCodeAndPic();
        BufferedImage image = (BufferedImage) map.get("codePic");
        //转base64便于传输
        String image2 = CodeUtil.BufferedImageToBase64(image);
        String code = String.valueOf(map.get("code"));
        String ip = request.getRemoteAddr();
        //实际开发中token为主键id+随机的uuid
        String token= UUID.randomUUID().toString().replaceAll("-", "");
        Jedis jedis = redisUtil.getJedis();
        //将验证码放入缓存中
        jedis.set(token,code);
        //设置验证码超时时间
        jedis.expire(token, 300);
        CaptchaResult captchaResult = new CaptchaResult();
        captchaResult.setImg(image2);
        captchaResult.setToken(token);
        return ResultUtils.success("ok",captchaResult);
    }
    @PostMapping("/login")
    public ResultVo login(@RequestBody LoginParm parm) {
        if (StringUtils.isEmpty(parm.getCaptcha())||StringUtils.isEmpty(parm.getToken())) {
            return ResultUtils.error("验证码不能为空");
        }
        Jedis jedis = redisUtil.getJedis();
        if (jedis.get(parm.getToken())==null) return ResultUtils.error("验证码超时,请刷新");
        String captcha = jedis.get(parm.getToken());
        //先判断验证码是否正确
        if(!captcha.equalsIgnoreCase(parm.getCaptcha().trim())) return ResultUtils.error("验证码错误");
        if (StringUtils.isEmpty(parm.getUsername()) || StringUtils.isEmpty(parm.getPassword()) || StringUtils.isEmpty(parm.getUserType())) {
            return ResultUtils.error("用户名或密码不能为空!");
        }

        //获取密码
        String password = DigestUtils.md5DigestAsHex(parm.getPassword().getBytes());
        if (parm.getUserType().equals("1")) {
            //构造查询条件
            QueryWrapper<SysUser> query = new QueryWrapper<>();
            query.lambda().eq(SysUser::getUsername, parm.getUsername()).eq(SysUser::getPassword, password);
            SysUser user = sysUserService.getOne(query);
            if (user == null) {
                return ResultUtils.error("用户名或密码错误!");
            }
            //生成token
            Map<String, String> map = new HashMap<>();
            map.put("userId", Long.toString(user.getUserId()));
            map.put("username", user.getUsername());
            String token = jwtUtils.generateToken(map);
            //设置返回值
            LoginResult result = new LoginResult();
            result.setUserId(user.getUserId());
            result.setToken(token);
            result.setUsername(user.getUsername());
            result.setUserType(parm.getUserType());
            return ResultUtils.success("登录成功", result);
        } else {
            //构造查询条件
            QueryWrapper<SchoolStudent> query = new QueryWrapper<>();
            query.lambda().eq(SchoolStudent::getStuNum, parm.getUsername())
                    .eq(SchoolStudent::getPassword, password);
            //查询学生信息
            SchoolStudent student = schoolStudentService.getOne(query);
            if (student == null) {
                return ResultUtils.error("用户名或密码错误!");
            }
            //生成token
            Map<String, String> map = new HashMap<>();
            map.put("userId", Long.toString(student.getStuId()));
            map.put("username", student.getStuName());
            String token = jwtUtils.generateToken(map);
            //设置返回值
            LoginResult result = new LoginResult();
            result.setUserId(student.getStuId());
            result.setToken(token);
            result.setUsername(student.getStuName());
            result.setUserType(parm.getUserType());
            return ResultUtils.success("登录成功", result);
        }

    }

    @GetMapping("/getInfo")
    public ResultVo getInfo(Long userId, String userType) {
        if (userType.equals("1")) {//管理员
            //查询用户信息
            SysUser user = sysUserService.getById(userId);
            UserInfo userInfo = new UserInfo();
            userInfo.setIntroduction(user.getNickName());
            userInfo.setName(user.getUsername());
            userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            //查询用户的权限字段
            List<SysMenu> menuList = null;
            if (user.getIsAdmin().equals("1")) {
                menuList = sysMenuService.list();
            } else {
                menuList = sysMenuService.getMenuByUserId(userId);
            }
            //获取code字段
            List<String> collect = menuList.stream().filter(item -> item != null && item.getCode() != null && StringUtils.isNotEmpty(item.getCode()))
                    .map(item -> item.getCode()).collect(Collectors.toList());
            if (collect.size() == 0) {
                return ResultUtils.error("为分配权限，请联系管理员!");
            }
            String[] array = collect.toArray(new String[collect.size()]);
            //设置用户的权限字段
            userInfo.setRoles(array);
            return ResultUtils.success("查询成功", userInfo);
        } else {
            //查询用户信息
            SchoolStudent user = schoolStudentService.getById(userId);
            UserInfo userInfo = new UserInfo();
            userInfo.setIntroduction(user.getStuName());
            userInfo.setName(user.getStuName());
            userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            //查询用户的权限字段
            List<SysMenu> menuList = sysMenuService.getStuMenuByUserId(userId);
            //获取code字段
            List<String> collect = menuList.stream().filter(item -> item != null && item.getCode() != null && StringUtils.isNotEmpty(item.getCode()))
                    .map(item -> item.getCode()).collect(Collectors.toList());
            if (collect.size() == 0) {
                return ResultUtils.error("为分配权限，请联系管理员!");
            }
            String[] array = collect.toArray(new String[collect.size()]);
            //设置用户的权限字段
            userInfo.setRoles(array);
            return ResultUtils.success("查询成功", userInfo);
        }
    }

    //查询菜单
    @GetMapping("/getMenuList")
    public ResultVo getMenuList(Long userId, String userType) {
        //根据用户id查询菜单
        List<SysMenu> menuList = null;
        if (userType.equals("1")) {
            //查询用户信息
            SysUser user = sysUserService.getById(userId);
            if (user.getIsAdmin().equals("1")) {
                menuList = sysMenuService.list();
            } else {
                menuList = sysMenuService.getMenuByUserId(userId);
            }
        } else {
            menuList = sysMenuService.getStuMenuByUserId(userId);
        }
        if (menuList == null || menuList.size() == 0) {
            return ResultUtils.error("暂未分配权限，请联系管理员!");
        }
        //帅选出菜单,类型是 目录和菜单
        List<SysMenu> collect = menuList.stream().filter(item -> item != null && !item.getType().equals("2"))
                .collect(Collectors.toList());
        //组装路由数据
        List<RouterVO> router = MakeTree.makeRouter(collect, 0L);
        return ResultUtils.success("查询成功", router);
    }
}
