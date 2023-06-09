package com.switchsky.web.drom_build.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.switchsky.web.drom_build.entity.BuildEcharts;
import com.switchsky.web.drom_build.entity.DromBuild;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface DromBuildService extends IService<DromBuild> {
    //新增
    void addBuild(DromBuild dromBuild);
    BuildEcharts getListBuild();
}
