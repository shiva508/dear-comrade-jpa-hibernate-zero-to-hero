package com.comrade.service;

import com.comrade.entity.NewsEntity;
import com.comrade.entity.OpinionEntity;
import com.comrade.model.NewsModel;
import com.comrade.model.OpinionModel;

import java.util.List;

public interface OpinionService {

    OpinionModel save(OpinionModel opinionModel);

    List<OpinionEntity> fetchAll();
}
