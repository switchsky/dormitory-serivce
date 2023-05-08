package com.switchsky.web.school_class.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.switchsky.web.school_class.entity.AssignClass;
import com.switchsky.web.school_class.entity.AssignClassParm;
import com.switchsky.web.school_class.entity.ListParm;
import com.switchsky.web.school_class.entity.SchoolClass;
import com.switchsky.web.school_class.mapper.SchoolClassMapper;
import com.switchsky.web.school_class.service.SchoolClassService;
import org.springframework.stereotype.Service;

/**
 * @Author thf
 * @Version 3501754007
 */
@Service
public class SchoolClassServiceImpl extends ServiceImpl<SchoolClassMapper, SchoolClass> implements SchoolClassService {
    @Override
    public SchoolClass getSchoolClassById(Long classId) {
        return this.baseMapper.getSchoolClassById(classId);
    }

    @Override
    public IPage<SchoolClass> getList(ListParm parm) {
        //构造分页对象
        IPage<SchoolClass> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        return this.baseMapper.getList(page,parm);
    }

    @Override
    public IPage<AssignClass> getAssignClass(AssignClassParm parm) {
        //构造分页对象
        IPage<AssignClass> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        return this.baseMapper.getAssignClass(page,parm);
    }
}
