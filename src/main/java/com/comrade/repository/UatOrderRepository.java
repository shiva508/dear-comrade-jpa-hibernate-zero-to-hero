package com.comrade.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Profile("uat")
public interface UatOrderRepository extends CommonRepository {
    @Override
    @Query(value = " select count(*) from TBL_ORDER",nativeQuery = true)
    Long recordsCount();
}
