package com.comrade.repository;

import com.comrade.entity.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>{

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


}
