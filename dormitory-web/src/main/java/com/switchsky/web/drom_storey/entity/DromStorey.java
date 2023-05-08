package com.switchsky.web.drom_storey.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author thf
 * @Version 3501754007
 */
@Data
@TableName("drom_storey")
public class DromStorey {
    @TableId(type = IdType.AUTO)
    private Long storeyId;
    private Long buildId;
    private String storeyName;
    private Integer orderNum;
}
