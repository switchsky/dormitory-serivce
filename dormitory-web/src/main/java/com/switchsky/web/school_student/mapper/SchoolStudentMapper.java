package com.switchsky.web.school_student.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.switchsky.web.school_student.entity.SchoolStudent;
import com.switchsky.web.school_student.entity.StuNumByClassDto;
import com.switchsky.web.school_student.entity.StuParm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface SchoolStudentMapper extends BaseMapper<SchoolStudent> {
    SchoolStudent getStuById(@Param("stuId") Long stuId);
    IPage<SchoolStudent> getList(IPage<SchoolStudent> page, @Param("parm")StuParm parm);
    SchoolStudent getById(@Param("stuId") Long stuId);
    List<Integer> getTotal();

    List<StuNumByClassDto> getClassStuNum();
}
