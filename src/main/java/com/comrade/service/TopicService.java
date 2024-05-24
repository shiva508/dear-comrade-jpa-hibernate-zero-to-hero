package com.comrade.service;

import com.comrade.entity.TopicEntity;
import com.comrade.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("topicService")
@AllArgsConstructor
public class TopicService implements CommonService<TopicEntity>{
    private final TopicRepository topicRepository;
    @Override
    public TopicEntity save(TopicEntity topicEntity) {
        return topicRepository.save(topicEntity);
    }

    @Override
    public List<TopicEntity> findAll() {
        return topicRepository.findAll();
    }
}
