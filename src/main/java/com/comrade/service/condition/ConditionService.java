package com.comrade.service.condition;

import com.comrade.domine.ConditionModel;
import com.comrade.entity.ConditionEntity;

import java.util.List;

public interface ConditionService {
    public ConditionEntity save(ConditionModel conditionModel);

    public ConditionEntity update(ConditionModel conditionModel);

    public ConditionEntity delete(ConditionModel conditionModel);

    public List<ConditionEntity> getAll();
}
