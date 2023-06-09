package com.switchsky.web.drom_leave.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.switchsky.web.drom_leave.entity.DromLeave;
import com.switchsky.web.drom_leave.entity.LeaveNumVo;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface DromLeaveMapper extends BaseMapper<DromLeave> {
    List<LeaveNumVo> getLeaveNum();
}
