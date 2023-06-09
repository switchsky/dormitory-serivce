package com.switchsky.web.dorm_room.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.switchsky.web.drom_room_bed.entity.DromRoomVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
@TableName("dorm_room")
public class DormRoom {
    @TableId(type = IdType.AUTO)
    private Long roomId;
    private Long storeyId;
    private String rootType;
    private String roomCode;
    private Integer totalBed;
    //床位列表
    @TableField(exist = false)
    List<DromRoomVo> bedList = new ArrayList<>();
}
