package com.itmk.web.school_student.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.school_student.entity.SchoolStudent;
import com.itmk.web.school_student.entity.StuParm;



/**
 * @Author java实战基地
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
}
