package com.switchsky.web.drom_look.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class LookParm {
     private String lookRoom;
    private String userName;
    private Long currentPage;
    private Long pageSize;
}
