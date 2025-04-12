package com.comrade.service;

import com.comrade.entity.NewsEntity;
import com.comrade.model.NewsModel;
import com.comrade.model.SearchModel;
import com.comrade.model.SearchResultModel;

import java.util.List;

public interface NewsService {

    NewsModel save(NewsModel newsModel);

    List<NewsEntity> fetchAll();

    SearchResultModel fetchBySearchCriteria(SearchModel searchModel);

    SearchResultModel searchByQueryDsl(SearchModel searchModel);
}
