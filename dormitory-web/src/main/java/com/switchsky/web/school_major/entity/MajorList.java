package com.switchsky.web.school_major.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class MajorList {
    private String majorName;
    private String collageName;
    private Long currentPage;
    private Long pageSize;
}
