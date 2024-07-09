package com.comrade.repository;

import com.comrade.entity.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParagraphRepository extends JpaRepository<Paragraph,Long> {
}
