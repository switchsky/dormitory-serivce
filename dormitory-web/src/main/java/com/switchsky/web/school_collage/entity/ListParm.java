package com.switchsky.web.school_collage.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class ListParm {
    private Long currentPage;
    private Long pageSize;
    private String collageName;
}
