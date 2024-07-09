package com.comrade.service;

import com.comrade.entity.TopicDetailsEntity;
import com.comrade.entity.TopicEntity;
import com.comrade.repository.ParagraphRepository;
import com.comrade.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service("topicService")
@AllArgsConstructor
public class TopicService implements CommonService<TopicEntity>{

    private final TopicRepository topicRepository;

    private final GkService gkService;

    private final TransactionTemplate transactionTemplate;

    private final ParagraphRepository paragraphRepository;

    @Transactional
    @Override
    public TopicEntity save(TopicEntity topicEntity) {
        TopicDetailsEntity topicDetailsEntity = topicEntity.getTopicDetailsEntity();
        topicEntity.addDetails(topicDetailsEntity);
        return topicRepository.save(topicEntity);
    }

    @Transactional
    @Override
    public List<TopicEntity> findAll() {
        return topicRepository.findAll();
    }

    @Transactional
    @Override
    public List<TopicEntity> externalServiceCallBeforeDbCall() {
        gkService.sleepProcessor();
        return topicRepository.findAll();
    }

    //@Transactional
    @Override
    public List<TopicEntity> externalServiceCallAfterDbCall() {
        List<TopicEntity> topicEntities = transactionTemplate.execute(status -> topicRepository.findAll());
        gkService.sleepProcessor();
        return topicEntities;
    }

    //@Transactional
    @Override
    public List<TopicEntity> creatingNewTransaction() {
        List<TopicEntity> topicEntities = transactionTemplate.execute(status -> topicRepository.findAll());
        gkService.saveTopicType();
        return topicEntities;
    }

    public void saveThreeDifferentSave(){

    }


}
