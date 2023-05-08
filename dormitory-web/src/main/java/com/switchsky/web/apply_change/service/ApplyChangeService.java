package com.switchsky.web.apply_change.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.switchsky.web.apply_change.entity.ApplyChange;
import com.switchsky.web.apply_change.entity.ApplyDoParm;
import com.switchsky.web.apply_change.entity.ApplyParm;

/**
 * @Author thf
 * @Version 3501754007
 */
public interface ApplyChangeService extends IService<ApplyChange> {
     void applySave(ApplyParm parm);
     void applyDo(ApplyDoParm change);
}
