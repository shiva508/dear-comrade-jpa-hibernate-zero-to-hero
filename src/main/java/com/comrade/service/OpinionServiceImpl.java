package com.comrade.service;

import com.comrade.entity.NewsEntity;
import com.comrade.entity.OpinionEntity;
import com.comrade.exception.NewsException;
import com.comrade.mapper.OpinionEntityMapper;
import com.comrade.model.OpinionModel;
import com.comrade.repository.NewsRepository;
import com.comrade.repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpinionServiceImpl implements OpinionService{

    private final OpinionEntityMapper opinionEntityMapper;

    private final OpinionRepository opinionRepository;

    private final NewsRepository newsRepository;

    @Transactional
    @Override
    public OpinionModel save(OpinionModel opinionModel) {
        try {
            log.info("save::started");
            OpinionModel finalOpinionModel = opinionModel;
            NewsEntity newsEntity = newsRepository.findById(opinionModel.getNewsId()).orElseThrow(() -> {
                throw new NewsException(String.format("News not found with id: %s", finalOpinionModel.getNewsId()));
            });
            OpinionEntity opinionEntity = opinionEntityMapper.modelToEntityMapper(opinionModel);
            opinionEntity = opinionRepository.save(opinionEntity);
            opinionModel = opinionEntityMapper.entityToModelMapper(opinionEntity);
            log.info("save::started");
            return opinionModel;
        } catch (Exception exception){
            log.error("save::error ",exception);
            throw new RuntimeException(exception.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<OpinionEntity> fetchAll() {
        try {
            log.info("fetchAll::started");
            List<OpinionEntity> all = opinionRepository.findAll();
            log.info("fetchAll::completed::count={}",all.size());
            return all;
        } catch (Exception exception){
            log.error("fetchAll::error ",exception);
            throw new RuntimeException(exception.getMessage());
        }
    }
}
