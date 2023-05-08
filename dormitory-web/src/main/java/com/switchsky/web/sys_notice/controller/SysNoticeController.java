package com.switchsky.web.sys_notice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.switchsky.config.sensitive.SensitiveUtil;
import com.switchsky.utils.ResultUtils;
import com.switchsky.utils.ResultVo;
import com.switchsky.web.sys_notice.entity.NoticeParm;
import com.switchsky.web.sys_notice.entity.SensitiveWords;
import com.switchsky.web.sys_notice.entity.SysNotice;
import com.switchsky.web.sys_notice.service.SysNoticeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/notice")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;
    @Autowired
    private SensitiveUtil sensitiveUtil;
    //新增
    @PostMapping
    public ResultVo add(@RequestBody SysNotice sysNotice){
        sysNotice.setCreateTime(new Date());
        SensitiveWords sensitiveVo = new SensitiveWords();
        //过滤敏感词
        String noticeTitle = sysNotice.getNoticeTitle();
        String noticeText = sysNotice.getNoticeText();
        Boolean haveSensitiveWords = sensitiveUtil.contains(noticeTitle)||sensitiveUtil.contains(noticeText);
        sensitiveVo.setTitle(sensitiveUtil.getSensitiveWord(noticeTitle));
        sensitiveVo.setPassage(sensitiveUtil.getSensitiveWord(noticeText));
        if(haveSensitiveWords) return ResultUtils.success("sensitive",sensitiveVo);
        boolean save = sysNoticeService.save(sysNotice);
        if(save){
            return ResultUtils.success("新增成功");
        }
        return ResultUtils.error("新增失败!");
    }
    //编辑
    @PutMapping
    public ResultVo edit(@RequestBody SysNotice sysNotice){
        SensitiveWords sensitiveVo = new SensitiveWords();
        String noticeTitle = sysNotice.getNoticeTitle();
        String noticeText = sysNotice.getNoticeText();
        Boolean haveSensitiveWords = sensitiveUtil.contains(noticeTitle)||sensitiveUtil.contains(noticeText);
        sensitiveVo.setTitle(sensitiveUtil.getSensitiveWord(noticeTitle));
        sensitiveVo.setPassage(sensitiveUtil.getSensitiveWord(noticeText));
        if(haveSensitiveWords) return ResultUtils.success("sensitive",sensitiveVo);
        boolean save = sysNoticeService.updateById(sysNotice);
        if(save){
            return ResultUtils.success("编辑成功");
        }
        return ResultUtils.error("编辑失败!");
    }

    //删除
    @DeleteMapping("/{noticeId}")
    public ResultVo delete(@PathVariable("noticeId") Long noticeId){
        boolean b = sysNoticeService.removeById(noticeId);
        if(b){
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败!");
    }
    //列表查询
    @GetMapping("/list")
    public ResultVo list(NoticeParm parm){
        //构造分页对象
        IPage<SysNotice> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        //查询条件
        QueryWrapper<SysNotice> query = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(parm.getNoticeTitle())){
            query.lambda().like(SysNotice::getNoticeTitle,parm.getNoticeTitle());
        }
        query.lambda().orderByDesc(SysNotice::getCreateTime);
        IPage<SysNotice> list = sysNoticeService.page(page, query);
        return ResultUtils.success("查询成功",list);
    }
    //首页公告
    @GetMapping("/getTopList")
    public ResultVo getTopList(){
        QueryWrapper<SysNotice> query = new QueryWrapper<>();
        query.lambda().orderByDesc(SysNotice::getCreateTime).last("limit 10");
        List<SysNotice> list = sysNoticeService.list(query);
        return ResultUtils.success("查询成功",list);
    }
}
