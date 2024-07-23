package com.comrade.service.rule;


import com.comrade.domine.RuleModel;
import com.comrade.entity.RuleEntity;

import java.util.List;


public interface RuleService {
    public RuleEntity save(RuleModel ruleModel);

    public RuleEntity update(RuleModel ruleModel);

    public RuleEntity delete(RuleModel ruleModel);

    public List<RuleEntity> getAll();
}
