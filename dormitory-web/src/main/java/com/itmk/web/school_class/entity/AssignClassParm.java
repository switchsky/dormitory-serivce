package com.itmk.web.school_class.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class AssignClassParm {
    private Long currentPage;
    private Long pageSize;
    private String className;
    private String classYear;
}
