package com.comrade.repository.h2;

import com.comrade.entity.h2.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
