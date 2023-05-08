package com.switchsky.web.school_student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.switchsky.web.school_student.entity.SchoolStudent;
import com.switchsky.web.school_student.entity.StuNumByClassDto;
import com.switchsky.web.school_student.entity.StuParm;

import java.util.List;


/**
 * @Author thf
 * @Version 3501754007
 */
public interface SchoolStudentService extends IService<SchoolStudent> {
    SchoolStudent getStuById(Long stuId);
    IPage<SchoolStudent> getList(StuParm parm);
    //新增
    void addStu(SchoolStudent schoolStudent);
    //编辑
    void editStu(SchoolStudent schoolStudent);
    //删除
    void deleteStu(Long studId);
    //查询学生信息
    SchoolStudent getById(Long studId);

    List<Integer> getTotal();

    List<StuNumByClassDto> getClassStuNum();
}
