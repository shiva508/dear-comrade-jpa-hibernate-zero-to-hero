package com.comrade.repository;

import com.comrade.entity.NewsEntity;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>,
                                        QuerydslPredicateExecutor<NewsEntity>,
                                        JpaSpecificationExecutor<NewsEntity> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
    attributePaths = {
            "newsId",
            "newsTitle",
            "newsAuthor",
            "createdAt",
            "modifiedAt",
            "opinions"
    })
    Page<NewsEntity> findAll(Specification<NewsEntity> specification, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "newsId",
                    "newsTitle",
                    "newsAuthor",
                    "createdAt",
                    "modifiedAt",
                    "opinions"
    })
    Page<NewsEntity> findAll(Predicate predicate, Pageable pageable);

}
