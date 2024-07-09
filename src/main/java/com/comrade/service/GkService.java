package com.comrade.service;

import com.comrade.entity.TopicTypeEntity;
import com.comrade.repository.TopicTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GkService {

    private final TopicTypeRepository topicTypeRepository;

    public void sleepProcessor() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveTopicType(){
        topicTypeRepository.save(TopicTypeEntity.builder().topicTypeName("JAVA").build());
    }
}
