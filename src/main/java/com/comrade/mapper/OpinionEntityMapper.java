package com.comrade.mapper;

import com.comrade.entity.NewsEntity;
import com.comrade.entity.OpinionEntity;
import com.comrade.model.OpinionModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OpinionEntityMapper {

   public OpinionEntity modelToEntityMapper(OpinionModel opinionModel){
       OpinionEntity opinionEntity = new OpinionEntity();
       BeanUtils.copyProperties(opinionModel, opinionEntity);
       NewsEntity newsEntity = new NewsEntity();
       newsEntity.setNewsId(opinionModel.getNewsId());
       opinionEntity.setNewsEntity(newsEntity);
       return opinionEntity;
    }

    public OpinionModel entityToModelMapper(OpinionEntity opinionEntity){
       OpinionModel opinionModel = new OpinionModel();
       BeanUtils.copyProperties(opinionEntity, opinionModel);
       opinionModel.setNewsId(opinionEntity.getNewsEntity().getNewsId());
       return opinionModel;
    }
}
