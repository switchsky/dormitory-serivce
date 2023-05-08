package com.switchsky.web.drom_leave.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.switchsky.web.drom_leave.entity.DromLeave;
import com.switchsky.web.drom_leave.entity.LeaveNumVo;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface DromLeaveService extends IService<DromLeave> {
    List<LeaveNumVo> getLeaveNum();
}
