package com.itmk.web.drom_room_bed.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class DromRoomVo {
    private Long bedId;
    private Long roomId;
    private Long stuId;
    private String bedCode;
    private String stuName;
    private String className;
}
