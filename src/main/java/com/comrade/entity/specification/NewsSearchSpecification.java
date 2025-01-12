package com.comrade.entity.specification;

import com.comrade.entity.NewsEntity;
import com.comrade.entity.OpinionEntity;
import com.comrade.model.SearchCriteria;
import com.comrade.util.DcConstants;
import com.comrade.util.OperationType;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class NewsSearchSpecification implements Specification<NewsEntity> {
    private List<SearchCriteria> searchCriterias;

    public NewsSearchSpecification(){
        searchCriterias = new ArrayList<>();
    }

    public void addSearchCriteria(SearchCriteria searchCriteria){
        searchCriterias.add(searchCriteria);
    }


    @Override
    public Predicate toPredicate(Root<NewsEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        searchCriterias.forEach(searchCriteria -> {
            if (searchCriteria.isChildOperation()){
                Join<Object, OpinionEntity> opinionEntities = root.join("opinions");
                predicates.add(query.where(this.childEqualsQueryBuilder(criteriaBuilder, searchCriteria, opinionEntities)).getRestriction());
            }else {
                if (searchCriteria.getOperation().equals(OperationType.EQUAL)){
                    predicates.add(getEqualQuery(root, criteriaBuilder, searchCriteria));
                }

                if (searchCriteria.getOperation().equals(OperationType.IN)){
                    predicates.add(getInQuery(root, criteriaBuilder, searchCriteria));

                }
                if (searchCriteria.getOperation().equals(OperationType.OR)){
                    largeDataQueryBuilder(root, criteriaBuilder, searchCriteria, predicates);
                }

                if (searchCriteria.getOperation().equals(OperationType.BETWEEN)){
                    predicates.add(getBetweenQuery(root, criteriaBuilder, searchCriteria));
                }

                if (searchCriteria.getOperation().equals(OperationType.LIKE)){
                    predicates.add(getLikeQuery(root, criteriaBuilder, searchCriteria));
                }
            }
        });
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }

    public Predicate getLikeQuery(Root<NewsEntity> root, CriteriaBuilder criteriaBuilder, SearchCriteria searchCriteria) {
        return criteriaBuilder.like(root.get(searchCriteria.getKey()), DcConstants.OP_LIKE + searchCriteria.getValue() + DcConstants.OP_LIKE);
    }

    public Predicate getBetweenQuery(Root<NewsEntity> root, CriteriaBuilder criteriaBuilder, SearchCriteria searchCriteria) {
        return criteriaBuilder.between(root.get(searchCriteria.getKey()), searchCriteria.getFrom(), searchCriteria.getTo());
    }

    public CriteriaBuilder.In<Object> getInQuery(Root<NewsEntity> root, CriteriaBuilder criteriaBuilder, SearchCriteria searchCriteria) {
        return criteriaBuilder.in(root.get(searchCriteria.getKey())).value(searchCriteria.getValue());
    }

    public Predicate getEqualQuery(Root<NewsEntity> root, CriteriaBuilder criteriaBuilder, SearchCriteria searchCriteria) {
        return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getKey());
    }

    private void largeDataQueryBuilder(Root<NewsEntity> root, CriteriaBuilder criteriaBuilder, SearchCriteria searchCriteria, List<Predicate> predicates) {
        if (null != searchCriteria.getValue()){
          List<String> inputs = (List<String>) searchCriteria.getValue();
          if (inputs.size()>1000){
              int chunkSize = chunkSizeFinder(inputs.size());
              AtomicInteger limitCounter = new AtomicInteger();

              List<List<String>> subValue = new ArrayList<>(inputs.stream().collect(Collectors.groupingBy(ls -> limitCounter.incrementAndGet() / 1000)).values());
              String[] expectedChunkKeys = IntStream.rangeClosed(1, chunkSize).mapToObj(value -> searchCriteria.getKey()).toArray(String[]::new);
              Predicate[] orPredicates = new Predicate[expectedChunkKeys.length];
              for (int i = 0; i < expectedChunkKeys.length; i++) {
                  orPredicates[i] = criteriaBuilder.in(root.get(expectedChunkKeys[i])).value(subValue.get(i));
              }
              Predicate orConditionPredicate = criteriaBuilder.or(orPredicates);
              predicates.add(orConditionPredicate);
          } else {
              String[] keyFields = searchCriteria.getKey().split(",");
              Predicate[] orCondition = new Predicate[keyFields.length];
              for (int i = 0; i < keyFields.length; i++) {
                  orCondition[i] = criteriaBuilder.in(root.get(keyFields[i])).value(searchCriteria.getValue());
              }
              Predicate orConditionPredicate = criteriaBuilder.or(orCondition);
              predicates.add(orConditionPredicate);
          }
        }
    }

    public int chunkSizeFinder(int size) {
        int quotient = size / 1000;
        int remainder = size % 1000;
        if (remainder>0){
            quotient += 1;
        }
        return quotient;
    }

    public Predicate childEqualsQueryBuilder(CriteriaBuilder criteriaBuilder, SearchCriteria searchCriteria, Join<Object, OpinionEntity> opinionEntities) {
        return criteriaBuilder.equal(opinionEntities.get(searchCriteria.getKey()), searchCriteria.getValue());
    }
}
