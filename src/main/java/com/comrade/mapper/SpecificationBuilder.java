package com.comrade.mapper;

import com.comrade.entity.NewsEntity;
import com.comrade.entity.OpinionEntity;
import com.comrade.entity.specification.NewsSearchSpecification;
import com.comrade.model.SearchCriteria;
import com.comrade.util.OperationType;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SpecificationBuilder {



    public boolean isFilterApplied(String attributeValue){
        return StringUtils.isNotBlank(attributeValue);
    }

    public void queryGenerator(boolean filtered, NewsSearchSpecification specification, String attributeName, String attributeValue, OperationType operationType, boolean childOperation){
        if (filtered){
            specification.addSearchCriteria(SearchCriteria.builder().key(attributeName).childOperation(childOperation).value(attributeValue).operation(operationType).build());
        }
    }



    public Specification<NewsEntity> orderBy(String attributeName, String attributeValue){
        return (root, query, criteriaBuilder) -> switch (attributeValue){
               case "ASC" -> query.orderBy(criteriaBuilder.asc(root.get(attributeName))).getRestriction();
               case "DESC" -> query.orderBy(criteriaBuilder.desc(root.get(attributeName))).getRestriction();
               default -> query.orderBy(criteriaBuilder.desc(root.get(attributeName))).getRestriction();
           };
    }

    public Specification<NewsEntity> opinionCount(String attributeName, String attributeValue){
        return (root, query, criteriaBuilder) -> {
            Join<Object, OpinionEntity> opinionEntities = root.join("opinions");
            query.where(criteriaBuilder.equal(opinionEntities.get(attributeName), attributeValue));
            return query.getRestriction();
        };
    }
}
