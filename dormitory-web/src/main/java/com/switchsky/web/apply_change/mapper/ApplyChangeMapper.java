package com.switchsky.web.apply_change.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.switchsky.web.apply_change.entity.ApplyChange;
import com.switchsky.web.apply_change.entity.StuInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface ApplyChangeMapper extends BaseMapper<ApplyChange> {
    StuInfoVo getStuInfo(@Param("stuId") Long stuId);
}