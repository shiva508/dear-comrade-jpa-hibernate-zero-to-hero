package com.comrade.controller;

import com.comrade.entity.TopicEntity;
import com.comrade.service.CommonService;
import com.comrade.service.GkService;
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
    @Autowired
    GkService gkService;

    @PostMapping("/save")
    public TopicEntity save(@RequestBody TopicEntity topicEntity){
        return topicService.save(topicEntity);
    }

    @GetMapping("/all")
    public List<TopicEntity> findAll(){
        List<TopicEntity> topicEntities = topicService.findAll();
        gkService.sleepProcessor();
        return topicEntities;
    }

    @GetMapping("/externalServiceCallBeforeDbCall")
    public List<TopicEntity> externalServiceCallBeforeDbCall(){
        List<TopicEntity> topicEntities = topicService.externalServiceCallBeforeDbCall();
        return topicEntities;
    }

    @GetMapping("/externalServiceCallAfterDbCall")
    public List<TopicEntity> externalServiceCallAfterDbCall(){
        List<TopicEntity> topicEntities = topicService.externalServiceCallAfterDbCall();
        return topicEntities;
    }

    @GetMapping("/creatingNewTransaction")
    public List<TopicEntity> creatingNewTransaction(){
        List<TopicEntity> topicEntities = topicService.creatingNewTransaction();
        return topicEntities;
    }

}
