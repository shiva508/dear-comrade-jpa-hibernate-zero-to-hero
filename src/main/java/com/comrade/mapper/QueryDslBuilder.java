package com.comrade.mapper;

import com.comrade.entity.QNewsEntity;
import com.comrade.entity.QOpinionEntity;
import com.comrade.model.SearchModel;
import com.comrade.util.DcConstants;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class QueryDslBuilder {

    public static final QNewsEntity qNewsEntity = QNewsEntity.newsEntity;

    public Predicate dynamicQueryBuilder(SearchModel searchModel) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (StringUtils.isNotBlank(searchModel.getNewsTitle())){
            booleanBuilder.and(qNewsEntity.newsTitle.like(DcConstants.OP_LIKE+searchModel.getNewsTitle()+DcConstants.OP_LIKE));
        }
        if (StringUtils.isNotBlank(searchModel.getChildOpinionDesc())){
            booleanBuilder.and(qNewsEntity.opinions.any().leaderName.eq(searchModel.getChildOpinionDesc()));
        }

        return booleanBuilder.getValue();
    }
}
