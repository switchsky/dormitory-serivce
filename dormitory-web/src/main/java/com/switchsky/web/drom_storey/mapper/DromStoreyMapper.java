package com.switchsky.web.drom_storey.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.switchsky.web.drom_storey.entity.DromStorey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface DromStoreyMapper extends BaseMapper<DromStorey> {
    //新增
    void addDrom(@Param("list")List<DromStorey> list);
}
