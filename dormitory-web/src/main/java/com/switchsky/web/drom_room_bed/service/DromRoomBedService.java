package com.switchsky.web.drom_room_bed.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.switchsky.web.drom_room_bed.entity.DromRoomBed;
import com.switchsky.web.drom_room_bed.entity.DromRoomVo;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface DromRoomBedService extends IService<DromRoomBed> {
     List<DromRoomBed> getBedList(Long roomId);
      List<DromRoomVo> getSelectBedList(Long roomId);
}
