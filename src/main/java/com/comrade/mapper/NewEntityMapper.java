package com.comrade.mapper;

import com.comrade.entity.NewsEntity;
import com.comrade.entity.OpinionEntity;
import com.comrade.model.NewsModel;
import com.comrade.model.OpinionModel;
import com.comrade.model.SearchResultModel;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewEntityMapper {

    public NewsEntity modelToEntityMapper(NewsModel newsModel){
        NewsEntity newsEntity = new NewsEntity();
        List<OpinionModel> opinionModels = newsModel.getOpinions();
        BeanUtils.copyProperties(newsModel, newsEntity);
        newsEntity.getOpinions().clear();
        List<OpinionEntity> opinionEntities = opinionModels.stream().map(opinionModel -> {
            OpinionEntity opinionEntity = new OpinionEntity();
            BeanUtils.copyProperties(opinionModel, opinionEntity);
            return opinionEntity;
        }).toList();
        newsEntity.setOpinions(opinionEntities);
        return newsEntity;
    }

    public NewsModel entityToModelMapper(NewsEntity newsEntity){
        NewsModel newsModel = new NewsModel();
        BeanUtils.copyProperties(newsEntity, newsModel);
        List<OpinionEntity> opinions = newsEntity.getOpinions();
        List<OpinionModel> opinionModels = opinions.stream().map(opinionEntity -> {
            OpinionModel opinionModel = new OpinionModel();
            BeanUtils.copyProperties(opinionEntity, opinionModel);
            return opinionModel;
        }).toList();
        newsModel.setOpinions(opinionModels);
        return newsModel;
    }

    public SearchResultModel searchResultMapper(Page<NewsEntity> newsEntityPage){
        SearchResultModel searchResultModel = new SearchResultModel();
        List<NewsModel> newsModels = newsEntityPage.getContent().stream().map(this::entityToModelMapper).toList();
        searchResultModel.setNews(newsModels);
        searchResultModel.setTotalElements(newsEntityPage.getTotalElements());
        searchResultModel.setTotalPages(newsEntityPage.getTotalPages());
        searchResultModel.setPageNumber(newsEntityPage.getNumber());
        searchResultModel.setPageSize(newsEntityPage.getSize());
        return searchResultModel;
    }
}
