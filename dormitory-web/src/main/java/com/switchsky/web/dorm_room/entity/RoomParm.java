package com.switchsky.web.dorm_room.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class RoomParm {
    private String area;
    private Integer start;
    private Integer end;
    private Integer stuNum;
    private Long storeyId;
     private String rootType;
}
