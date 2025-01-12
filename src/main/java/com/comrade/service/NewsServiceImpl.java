package com.comrade.service;

import com.comrade.entity.NewsEntity;
import com.comrade.entity.specification.NewsSearchSpecification;
import com.comrade.mapper.SpecificationBuilder;
import com.comrade.exception.NewsException;
import com.comrade.mapper.NewEntityMapper;
import com.comrade.model.NewsModel;
import com.comrade.model.SearchModel;
import com.comrade.model.SearchResultModel;
import com.comrade.repository.NewsRepository;
import com.comrade.util.OperationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;

    private final SpecificationBuilder specificationBuilder;

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

    @Override
    @Transactional
    public SearchResultModel fetchBySearchCriteria(SearchModel searchModel) {
        try {
            log.info("fetchBySearchCriteria::started");
            NewsSearchSpecification newsSearchSpecification = new NewsSearchSpecification();

            boolean isTitleFilter = specificationBuilder.isFilterApplied(searchModel.getNewsTitle());
            specificationBuilder.queryGenerator(isTitleFilter, newsSearchSpecification, "newsTitle",searchModel.getNewsTitle(), OperationType.LIKE, false);

            boolean isChildOpnDescTitleFilter = specificationBuilder.isFilterApplied(searchModel.getChildOpinionDesc());

            specificationBuilder.queryGenerator(isChildOpnDescTitleFilter, newsSearchSpecification, "leaderName", searchModel.getChildOpinionDesc(), OperationType.EQUAL, true);

            Pageable pageable = PageRequest.of(searchModel.getPage(), searchModel.getSize());
            Page<NewsEntity> newsEntities = newsRepository.findAll(newsSearchSpecification, pageable);

            SearchResultModel searchResultModel = newEntityMapper.searchResultMapper(newsEntities);
            log.info("fetchBySearchCriteria::started");
            return searchResultModel;
        } catch (Exception exception){
            log.error("fetchAll::error ",exception);
            throw new NewsException(exception.getMessage());
        }
    }
}
