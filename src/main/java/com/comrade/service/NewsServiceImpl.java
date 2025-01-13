package com.comrade.service;

import com.comrade.entity.NewsEntity;
import com.comrade.exception.NewsException;
import com.comrade.mapper.NewEntityMapper;
import com.comrade.model.NewsModel;
import com.comrade.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    private final NewEntityMapper newEntityMapper;

    @Override
    @Transactional
    public NewsModel save(NewsModel newsModel) {
        try {
            log.info("save::started");
            NewsEntity newsEntity = newEntityMapper.modelToEntityMapper(newsModel);
            newsEntity = newsRepository.save(newsEntity);
            newsModel = newEntityMapper.entityToModelMapper(newsEntity);
            log.info("save::completed");
            return newsModel;
        }catch (Exception exception){
            log.error("save::error ",exception);
            throw new NewsException(exception.getMessage());
        }
    }

    @Override
    @Transactional
    public List<NewsEntity> fetchAll() {
        try {
            log.info("fetchAll::started");
            List<NewsEntity> all = newsRepository.findAll();
            log.info("fetchAll::completed::count={}",all.size());
            return all;
        } catch (Exception exception){
            log.error("fetchAll::error ",exception);
            throw new NewsException(exception.getMessage());
        }
    }
}
