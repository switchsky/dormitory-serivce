package com.switchsky.web.drom_room_bed.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.switchsky.web.drom_room_bed.entity.DromRoomBed;
import com.switchsky.web.drom_room_bed.entity.DromRoomVo;
import com.switchsky.web.drom_room_bed.mapper.DromRoomBedMapper;
import com.switchsky.web.drom_room_bed.service.DromRoomBedService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
@Service
public class DromRoomBedServiceImpl extends ServiceImpl<DromRoomBedMapper, DromRoomBed> implements DromRoomBedService {
    @Override
    public List<DromRoomBed> getBedList(Long roomId) {
        return this.baseMapper.getBedList(roomId);
    }

    @Override
    public List<DromRoomVo> getSelectBedList(Long roomId) {
        return this.baseMapper.getSelectBedList(roomId);
    }
}
