package com.switchsky.web.select_bed.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.switchsky.web.select_bed.entity.ChangeStu;
import com.switchsky.web.select_bed.entity.StuBed;
import com.switchsky.web.select_bed.entity.StuBedVo;

import java.util.List;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface StuBedService extends IService<StuBed> {
    List<StuBedVo> getStuBedList(Long stuId);
    List<ChangeStu> getStuList(Long classId);
    void clearBed(Long classId);
}
