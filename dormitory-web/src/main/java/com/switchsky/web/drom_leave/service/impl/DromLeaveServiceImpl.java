package com.switchsky.web.drom_leave.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.switchsky.web.drom_leave.entity.DromLeave;
import com.switchsky.web.drom_leave.entity.LeaveNumVo;
import com.switchsky.web.drom_leave.mapper.DromLeaveMapper;
import com.switchsky.web.drom_leave.service.DromLeaveService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
@Service
public class DromLeaveServiceImpl extends ServiceImpl<DromLeaveMapper, DromLeave> implements DromLeaveService {
    @Override
    public List<LeaveNumVo> getLeaveNum() {
        return this.baseMapper.getLeaveNum();
    }
}
