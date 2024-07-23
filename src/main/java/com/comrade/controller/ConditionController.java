package com.comrade.controller;

import com.comrade.domine.ConditionModel;
import com.comrade.entity.ConditionEntity;
import com.comrade.service.condition.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/condition")
public class ConditionController {

    @Autowired
    private ConditionService conditionService;
    @PostMapping("/save")
    public ConditionEntity save(@RequestBody ConditionModel conditionModel){
        return conditionService.save(conditionModel);
    }

    @PutMapping("/update")
    public ConditionEntity update(@RequestBody ConditionModel conditionModel){
        return conditionService.update(conditionModel);
    }

    @DeleteMapping("/delete")
    public ConditionEntity delete(@RequestBody ConditionModel conditionModel){
        return conditionService.delete(conditionModel);
    }

    @GetMapping("/all")
    public List<ConditionEntity> getAll(){
        return conditionService.getAll();
    }
}
