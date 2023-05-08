package com.switchsky.web.school_major.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.switchsky.web.school_major.entity.MajorList;
import com.switchsky.web.school_major.entity.SchoolMajor;
import org.apache.ibatis.annotations.Param;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface SchoolMajorMapper extends BaseMapper<SchoolMajor> {
    IPage<SchoolMajor> getList(IPage<SchoolMajor> page, @Param("parm")MajorList majorList);
}
