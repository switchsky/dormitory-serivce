package com.switchsky.web.school_class.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.switchsky.web.school_class.entity.AssignClass;
import com.switchsky.web.school_class.entity.AssignClassParm;
import com.switchsky.web.school_class.entity.ListParm;
import com.switchsky.web.school_class.entity.SchoolClass;
import org.apache.ibatis.annotations.Param;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface SchoolClassMapper extends BaseMapper<SchoolClass> {
    //编辑回显
    SchoolClass getSchoolClassById(@Param("classId") Long classId);
    //查询列表
    IPage<SchoolClass> getList(IPage<SchoolClass> page, @Param("parm")ListParm parm);
    //分配宿舍，查询班级列表
    IPage<AssignClass> getAssignClass(IPage<AssignClass> page,@Param("parm") AssignClassParm parm);
}
