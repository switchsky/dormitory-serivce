package com.switchsky.web.drom_build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.switchsky.web.drom_build.entity.BuildVo;
import com.switchsky.web.drom_build.entity.DromBuild;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface DromBuildMapper extends BaseMapper<DromBuild> {
    List<BuildVo> getListBuild();
}
