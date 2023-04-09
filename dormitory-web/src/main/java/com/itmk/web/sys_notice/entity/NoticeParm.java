package com.itmk.web.sys_notice.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class NoticeParm {
    private Long currentPage;
    private Long pageSize;
    private String noticeTitle;
}
