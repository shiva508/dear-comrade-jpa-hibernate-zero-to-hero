package com.comrade.repository;

import com.comrade.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository extends JpaRepository<Order, Long> {
    Long recordsCount();
}
