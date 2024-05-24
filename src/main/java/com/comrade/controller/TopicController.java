package com.comrade.controller;

import com.comrade.entity.TopicEntity;
import com.comrade.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/topic")
public class TopicController {

    @Autowired
    @Qualifier("topicService")
    CommonService<TopicEntity> topicService;

    @PostMapping("/save")
    public TopicEntity save(@RequestBody TopicEntity topicEntity){
        return topicService.save(topicEntity);
    }

    @GetMapping("/all")
    public List<TopicEntity> findAll(){
        return topicService.findAll();
    }
}
