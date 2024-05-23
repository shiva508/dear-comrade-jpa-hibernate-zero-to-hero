package com.comrade.repository.postgres;

import com.comrade.entity.postgres.TopicType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicTypeRepository extends JpaRepository<TopicType,Long> {
}
