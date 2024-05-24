package com.comrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TOPIC_TYPE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOPIC_TYPE_ID")
    private Long topicTypeId;

    @Column(name = "TOPIC_TYPE_NAME")
    private String topicTypeName;

}
