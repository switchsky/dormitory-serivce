package com.itmk.web.dorm_room.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.dorm_room.entity.DormRoom;
import com.itmk.web.dorm_room.entity.RoomTree;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface DormRoomService extends IService<DormRoom> {
     List<RoomTree> getRoomTree(Long storeyId);
}
