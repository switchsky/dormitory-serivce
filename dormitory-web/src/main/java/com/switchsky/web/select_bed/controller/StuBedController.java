package com.switchsky.web.select_bed.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.switchsky.utils.ResultUtils;
import com.switchsky.utils.ResultVo;
import com.switchsky.web.apply_change.service.ApplyChangeService;
import com.switchsky.web.school_student.entity.SchoolStudent;
import com.switchsky.web.school_student.service.SchoolStudentService;
import com.switchsky.web.select_bed.entity.ChangeStu;
import com.switchsky.web.select_bed.entity.StuBed;
import com.switchsky.web.select_bed.entity.StuBedVo;
import com.switchsky.web.select_bed.service.StuBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author thf
 * @Version 3501754007
 */
@RestController
@RequestMapping("/api/stuBed")
public class StuBedController {
    @Autowired
    private StuBedService stuBedService;
    @Autowired
    private SchoolStudentService schoolStudentService;
    @Autowired
    private ApplyChangeService applyChangeService;
    @Transactional
    @PostMapping("/selectBedSave")
    public ResultVo selectBedSave(@RequestBody StuBed stuBed){
        //根据床位查询，是否已经被选择
        QueryWrapper<StuBed> query = new QueryWrapper<>();
        query.lambda().eq(StuBed::getBedId,stuBed.getBedId());
        StuBed bed = stuBedService.getOne(query);
        if(bed != null){
            return ResultUtils.error("该床位已经被占用!");
        }
        //查询学生是否已经选择床位
        QueryWrapper<StuBed> stuQuery = new QueryWrapper<>();
        stuQuery.lambda().eq(StuBed::getStuId,stuBed.getStuId());
        StuBed stu = stuBedService.getOne(stuQuery);
        if(stu != null){
            //先删后加
            QueryWrapper<StuBed> delquery = new QueryWrapper<>();
            System.out.println(stu.getBedId());
            delquery.lambda().eq(StuBed::getStuId,stu.getStuId())
                .eq(StuBed::getBedId,stu.getBedId());
            boolean remove = stuBedService.remove(delquery);
            if(remove){
                //保存选择的宿舍
                boolean save = stuBedService.save(stuBed);
                if(save) return ResultUtils.success("调换成功!");
            }
            return ResultUtils.error("调换失败!");
        } else {
            //保存选择的宿舍
            boolean save = stuBedService.save(stuBed);
            if(save){
                return ResultUtils.success("选择成功!");
            }
            return ResultUtils.error("选择失败!");
        }
    }

    //查询学生宿舍
    @GetMapping("/getStuBedList")
    public ResultVo getStuBedList(Long stuId,String userType){
        if(userType.equals("1")){
            return ResultUtils.success("查询成功",null);
        }
        List<StuBedVo> stuBedList = stuBedService.getStuBedList(stuId);
        return ResultUtils.success("查询成功",stuBedList);
    }

     //查询调换的学生
    @GetMapping("/getStuList")
    public ResultVo getStuList(Long stuId){
        //根据学生id查询班级
        SchoolStudent student = schoolStudentService.getById(stuId);
        //根据班级id查询学生
        List<ChangeStu> stuList = stuBedService.getStuList(student.getClassId());
        return ResultUtils.success("查询成功",stuList);
    }
    //删除
    @DeleteMapping("/{stuId}/{bedId}")
    public ResultVo deleteBed(@PathVariable("stuId") Long stuId,@PathVariable("bedId") Long bedId){
        QueryWrapper<StuBed> query = new QueryWrapper<>();
        query.lambda().eq(StuBed::getStuId,stuId)
                .eq(StuBed::getBedId,bedId);
        boolean remove = stuBedService.remove(query);
        if(remove){
            return ResultUtils.success("删除成功!");
        }
        return ResultUtils.error("删除失败!");
    }
}
