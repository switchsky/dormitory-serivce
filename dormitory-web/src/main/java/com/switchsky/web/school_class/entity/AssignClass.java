package com.switchsky.web.school_class.entity;

import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
public class AssignClass {
    private Long classId;
    private Long majorId;
    private String className;
    private String classYear;
    private Integer orderNum;
    private Integer stuCount;
    private Integer manCount;
    private Integer girlCount;
}
