package com.switchsky.web.select_bed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.switchsky.web.select_bed.entity.ChangeStu;
import com.switchsky.web.select_bed.entity.StuBed;
import com.switchsky.web.select_bed.entity.StuBedVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface StuBedMapper extends BaseMapper<StuBed> {
    List<StuBedVo> getStuBedList(@Param("stuId") Long stuId);
    List<ChangeStu> getStuList(@Param("classId") Long classId);
}
