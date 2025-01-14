package com.comrade.service;

import com.comrade.entity.NewsEntity;
import com.comrade.model.NewsModel;

import java.util.List;

public interface NewsService {

    NewsModel save(NewsModel newsModel);

    List<NewsEntity> fetchAll();

}
