package com.switchsky.web.school_student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.switchsky.web.school_student.entity.SchoolStudent;
import com.switchsky.web.school_student.entity.StuNumByClassDto;
import com.switchsky.web.school_student.entity.StuParm;
import com.switchsky.web.school_student.mapper.SchoolStudentMapper;
import com.switchsky.web.school_student.service.SchoolStudentService;
import com.switchsky.web.stu_role.entity.StuRole;
import com.switchsky.web.stu_role.service.StuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
@Service
public class SchoolStudentServiceImpl extends ServiceImpl<SchoolStudentMapper, SchoolStudent> implements SchoolStudentService {
    @Autowired
    private StuRoleService stuRoleService;
    @Override
    public SchoolStudent getStuById(Long stuId) {
        return this.baseMapper.getStuById(stuId);
    }

    @Override
    public IPage<SchoolStudent> getList(StuParm parm) {
        //构造分页对象
        IPage<SchoolStudent> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        return this.baseMapper.getList(page,parm);
    }

    @Override
    @Transactional
    public void addStu(SchoolStudent schoolStudent) {
        //保存学生信息
        int insert = this.baseMapper.insert(schoolStudent);
        //设置学生角色
        if(insert > 0){
            StuRole stuRole = new StuRole();
            stuRole.setStuId(schoolStudent.getStuId());
            stuRole.setRoleId(schoolStudent.getRoleId());
            stuRoleService.save(stuRole);
        }
    }

    @Override
    @Transactional
    public void editStu(SchoolStudent schoolStudent) {
        //编辑学生信息
        int i = this.baseMapper.updateById(schoolStudent);
        //设置角色
        if(i>0){
            //先删除，再插入
            QueryWrapper<StuRole> query = new QueryWrapper<>();
            query.lambda().eq(StuRole::getStuId,schoolStudent.getStuId());
            stuRoleService.remove(query);
            StuRole stuRole = new StuRole();
            stuRole.setStuId(schoolStudent.getStuId());
            stuRole.setRoleId(schoolStudent.getRoleId());
            stuRoleService.save(stuRole);
        }
    }

    @Override
    public void deleteStu(Long studId) {
        int i = this.baseMapper.deleteById(studId);
        if(i>0){
            QueryWrapper<StuRole> query = new QueryWrapper<>();
            query.lambda().eq(StuRole::getStuId,studId);
            stuRoleService.remove(query);
        }
    }


    @Override
    public SchoolStudent getById(Long studId) {
        return this.baseMapper.getStuById(studId);
    }

    @Override
    public List<Integer> getTotal() {
        return this.baseMapper.getTotal();
    }

    @Override
    public List<StuNumByClassDto> getClassStuNum() {
        return this.baseMapper.getClassStuNum();
    }
}
