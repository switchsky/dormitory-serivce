package com.switchsky.web.apply_change.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class ApplyListParm {
    private Long currentPage;
    private Long pageSize;
    private String userType;
    private Long userId;
}
